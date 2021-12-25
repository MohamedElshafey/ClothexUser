package com.clothex.user.voucher.scan

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

/**
 * Created by Mohamed Elshafey on 25/12/2021.
 */
class QRImageAnalyzer(val callback: (String) -> Unit) : ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    barcodes.firstOrNull()?.let {
                        it.rawValue?.let { value -> callback.invoke(value) }
                    }
                }
                .addOnFailureListener {}
                .addOnCompleteListener {
                    mediaImage.close()
                    imageProxy.close()
                }
        }
    }
}