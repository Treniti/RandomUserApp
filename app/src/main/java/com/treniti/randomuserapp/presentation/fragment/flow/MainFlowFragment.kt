package com.treniti.randomuserapp.presentation.fragment.flow

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.treniti.randomuserapp.R
import com.treniti.randomuserapp.databinding.FragmentMainFlowBinding
import com.treniti.randomuserapp.presentation.fragment.base.BaseFlowFragment
import com.treniti.randomuserapp.utils.viewBinding


class MainFlowFragment : BaseFlowFragment(R.layout.fragment_main_flow, R.id.navHostFragmentMain) {

    private val binding by viewBinding(FragmentMainFlowBinding::bind)

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}