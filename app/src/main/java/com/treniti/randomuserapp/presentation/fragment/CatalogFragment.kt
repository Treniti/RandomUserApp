package com.treniti.randomuserapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.treniti.randomuserapp.R
import com.treniti.randomuserapp.appComponent
import com.treniti.randomuserapp.data.ResultWrapper
import com.treniti.randomuserapp.data.presentation.UserPresentation
import com.treniti.randomuserapp.databinding.FragmentCatalogBinding
import com.treniti.randomuserapp.presentation.adapter.CatalogRecyclerViewAdapter
import com.treniti.randomuserapp.presentation.listener.CatalogItemListener
import com.treniti.randomuserapp.presentation.fragment.flow.MainFlowFragmentDirections
import com.treniti.randomuserapp.presentation.viewmodel.CatalogViewModel
import com.treniti.randomuserapp.utils.*
import javax.inject.Inject


class CatalogFragment : Fragment(R.layout.fragment_catalog), CatalogItemListener {

    private val binding by viewBinding(FragmentCatalogBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val userVideModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CatalogViewModel::class.java]
    }

    private lateinit var catalogRecyclerViewAdapter: CatalogRecyclerViewAdapter

    private val catalogList: MutableList<UserPresentation> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.inject(this)

        setupCatalogRecyclerViewAdapter()
        setupClickListeners()
    }


    private fun setupClickListeners() {
        binding.btnGetUsers.setOnClickListener {
            getUsers()
        }
    }

    private fun setupCatalogRecyclerViewAdapter() {
        catalogRecyclerViewAdapter = CatalogRecyclerViewAdapter(catalogList, this)
        binding.catalogRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.catalogRecyclerView.adapter = catalogRecyclerViewAdapter
    }

    private fun getUsers() {
        userVideModel.getUsers().observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    hideProgress()
                    fetchCatalogData(result.data)
                }

                is ResultWrapper.Error -> {
                    hideProgress()
                    result.message?.let { showMessage(requireContext(), it) }
                }

                is ResultWrapper.Loading -> {
                    showProgress()
                }
            }
        }
    }

    private fun fetchCatalogData(data: List<UserPresentation>) {
        catalogList.clear()
        catalogList.addAll(data)
        catalogRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun onCatalogItemSelected(catalogItem: UserPresentation) {
        val action =
            MainFlowFragmentDirections.actionMainFlowFragmentToUserInfoFragment(catalogItem)
        activityNavController().navigate(action)
    }
}