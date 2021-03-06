package com.clothex.user.voucher.scan

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentScanQrBinding
import com.clothex.user.dialog.MessageAlertDialog
import com.clothex.user.log.MainLogEvents
import com.clothex.user.utils.hasPermission
import com.clothex.user.voucher.add_text.CodeVoucherViewModel
import org.koin.android.ext.android.inject
import retrofit2.HttpException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Created by Mohamed Elshafey on 24/12/2021.
 */
class ScanQRFragment : Fragment() {

    private val viewModel: CodeVoucherViewModel by inject()
    private var _binding: FragmentScanQrBinding? = null
    private val binding get() = _binding!!
    private var cameraProvider: ProcessCameraProvider? = null
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var windowManager: WindowManager
    private var imageCapture: ImageCapture? = null
    private var imageAnalyzer: ImageAnalysis? = null
    private var displayId: Int = -1
    private var preview: Preview? = null
    private var camera: Camera? = null
    private val displayManager by lazy {
        requireContext().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    }
    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) = Unit
        override fun onDisplayRemoved(displayId: Int) = Unit
        override fun onDisplayChanged(displayId: Int) = view?.let { view ->
            if (displayId == this@ScanQRFragment.displayId) {
                imageCapture?.targetRotation = view.display.rotation
                imageAnalyzer?.targetRotation = view.display.rotation
            }
        } ?: Unit
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            permissionFlow()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.logScreen(ScanQRFragment::class.java.simpleName)
        viewModel.logEvent(MainLogEvents.AddQRVoucher)
        cameraExecutor = Executors.newSingleThreadExecutor()
        displayManager.registerDisplayListener(displayListener, null)
        windowManager = activity?.windowManager!!
        binding.viewFinder.post {
            displayId = binding.viewFinder.display.displayId
        }
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
    }

    override fun onResume() {
        super.onResume()
        permissionFlow()
    }

    private fun permissionFlow() {
        when {
            hasPermission(Manifest.permission.CAMERA) -> {
                newSetup()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                showDialog()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun newSetup() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            lensFacing = when {
                hasBackCamera() -> CameraSelector.LENS_FACING_BACK
                hasFrontCamera() -> CameraSelector.LENS_FACING_FRONT
                else -> throw IllegalStateException("Back and front camera are unavailable")
            }
            bindCameraUseCases()
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun hasBackCamera(): Boolean {
        return cameraProvider?.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA) ?: false
    }

    private fun hasFrontCamera(): Boolean {
        return cameraProvider?.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA) ?: false
    }

    private fun bindCameraUseCases() {
        if (activity == null) return
        val metrics = getScreenMetrics(requireActivity())
        val screenAspectRatio = aspectRatio(metrics.first, metrics.second)
        val rotation = binding.viewFinder.display.rotation
        val cameraProvider = cameraProvider
            ?: throw IllegalStateException("Camera initialization failed.")
        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        preview = Preview.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(rotation)
            .build()
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(rotation)
            .build()
        imageAnalyzer = ImageAnalysis.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(rotation)
            .build()
        imageAnalyzer!!.setAnalyzer(cameraExecutor, QRImageAnalyzer {
            binding.txtBarcodeValue.text = it
            if (viewModel.isSendVoucher.not()) {
                viewModel.logEvent(MainLogEvents.AddVoucher(it))
                viewModel.addVoucher(it)
            }
        })
        viewModel.responseLiveData.observe(viewLifecycleOwner) { result ->
            result.getOrNull()?.let {
                if (it.data == true)
                    findNavController().navigate(ScanQRFragmentDirections.actionScanQRFragmentToVoucherMessageFragment())
                if (it.message.isNullOrEmpty().not()) {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    findNavController().navigateUp()
                }
            }
            result.exceptionOrNull()?.let {
                Toast.makeText(requireContext(), (it as HttpException).message(), Toast.LENGTH_LONG)
                    .show()
            }
        }
        cameraProvider.unbindAll()
        try {
            camera = cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageCapture,
                imageAnalyzer
            )
            preview?.setSurfaceProvider(binding.viewFinder.surfaceProvider)
        } catch (exc: Exception) {
        }
    }

    private fun getScreenMetrics(activity: Activity): Pair<Int, Int> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = activity.windowManager.currentWindowMetrics
            Pair(windowMetrics.bounds.width(), windowMetrics.bounds.height())
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
        }
    }

    private fun showDialog() {
        MessageAlertDialog.showAlertDialog(
            requireContext(),
            getString(R.string.enable_camera),
            getString(R.string.enable_camera_msg),
            iconRes = R.drawable.ic_enable_camera,
            positiveButtonText = getString(R.string.go_to_settings),
            positiveOnClickListener = {
                openAppSettings()
            }
        )
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri: Uri = Uri.fromParts("package", context?.packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}