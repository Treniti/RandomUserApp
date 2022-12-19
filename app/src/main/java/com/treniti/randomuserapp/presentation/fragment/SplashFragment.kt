package com.treniti.randomuserapp.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.treniti.randomuserapp.R
import com.treniti.randomuserapp.utils.activityNavController

class SplashFragment : Fragment() {

    private val delayMillis = 2000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            activityNavController().navigate(R.id.action_splashFragment_to_mainFlowFragment)
        }, delayMillis)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}