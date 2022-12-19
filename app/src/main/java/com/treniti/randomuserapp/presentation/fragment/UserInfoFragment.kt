package com.treniti.randomuserapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.treniti.randomuserapp.R
import com.treniti.randomuserapp.databinding.FragmentUserInfoBinding
import com.treniti.randomuserapp.utils.viewBinding


class UserInfoFragment : Fragment(R.layout.fragment_user_info) {

    private val binding by viewBinding(FragmentUserInfoBinding::bind)

    private val args: UserInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUserUI()
    }

    private fun updateUserUI() {
        binding.fullNameTV.text = args.user?.fullName
        binding.ageTV.text = requireContext().getString(R.string.userAge, args.user?.age)
        binding.genderTV.text = requireContext().getString(R.string.userGender, args.user?.gender)
        binding.emailTV.text = requireContext().getString(R.string.userEmail, args.user?.email)
        binding.phoneTV.text = requireContext().getString(R.string.userPhone, args.user?.phone)
        downloadPhoto()
    }

    private fun downloadPhoto() {
        Glide.with(requireContext())
            .load(args.user?.photo)
            .into(binding.photo)
    }
}