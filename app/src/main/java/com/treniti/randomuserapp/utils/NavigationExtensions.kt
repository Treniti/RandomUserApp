package com.treniti.randomuserapp.utils


import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.treniti.randomuserapp.R

fun Fragment.activityNavController() = requireActivity().findNavController(R.id.navHostFragment)
