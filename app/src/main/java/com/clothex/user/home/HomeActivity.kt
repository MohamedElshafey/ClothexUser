package com.clothex.user.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.clothex.user.R
import com.clothex.user.base.BaseLanguageActivity
import com.clothex.user.databinding.ActivityHomeBinding
import com.clothex.user.home.categories.CategoriesFragment
import com.clothex.user.home.home.HomeFragment
import com.clothex.user.my_items.ItemsContainerFragment
import com.clothex.user.my_items.items.MyItemsFragment
import com.clothex.user.my_items.orders.ActiveOrdersFragment
import com.clothex.user.profile.ProfileFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HomeActivity : BaseLanguageActivity() {

    companion object {
        fun start(context: Context) =
            context.startActivity(Intent(context, HomeActivity::class.java))
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.apply {
            itemIconTintList = null
            setupWithNavController(findNavController(R.id.nav_host_fragment_activity_main))
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallback, true)
        val badge = binding.navView.getOrCreateBadge(R.id.navigation_profile)
        badge.isVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallback)
    }

    private val fragmentLifecycleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is CategoriesFragment)
                binding.navView.selectedItemId = R.id.navigation_categories
            val transition: Transition = Slide(Gravity.BOTTOM)
            when (f) {
                is HomeFragment, is ItemsContainerFragment, is MyItemsFragment, is ActiveOrdersFragment,
                is CategoriesFragment, is ProfileFragment, is BottomSheetDialogFragment -> {
                    if (binding.navView.visibility == GONE) {
                        TransitionManager.beginDelayedTransition(
                            binding.root,
                            transition.excludeTarget(R.id.nav_host_fragment_activity_main, true)
                        )
                    }
                    binding.navView.visibility = VISIBLE
                }
                else -> {
                    if (binding.navView.visibility == VISIBLE) {
                        TransitionManager.beginDelayedTransition(
                            binding.root,
                            transition.excludeTarget(R.id.nav_host_fragment_activity_main, true)
                        )
                    }
                    binding.navView.visibility = GONE
                }
            }
        }
    }

}