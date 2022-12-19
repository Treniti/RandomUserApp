package com.treniti.randomuserapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.treniti.randomuserapp.R
import com.treniti.randomuserapp.appComponent
import com.treniti.randomuserapp.data.ResultWrapper
import com.treniti.randomuserapp.data.presentation.UserPresentation
import com.treniti.randomuserapp.databinding.FragmentHistoryBinding
import com.treniti.randomuserapp.presentation.adapter.HistoryRecyclerViewAdapter
import com.treniti.randomuserapp.presentation.listener.HistoryItemListener
import com.treniti.randomuserapp.presentation.fragment.flow.MainFlowFragmentDirections
import com.treniti.randomuserapp.presentation.viewmodel.HistoryViewModel
import com.treniti.randomuserapp.utils.*
import javax.inject.Inject


class HistoryFragment : Fragment(R.layout.fragment_history), HistoryItemListener {

    private val binding by viewBinding(FragmentHistoryBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val historyVideModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
    }

    private lateinit var historyRecyclerViewAdapter: HistoryRecyclerViewAdapter

    private val historyList: MutableList<UserPresentation> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.inject(this)

        setupHistoryAdapter()

        getHistory()

    }

    private fun setupHistoryAdapter() {
        historyRecyclerViewAdapter = HistoryRecyclerViewAdapter(historyList, this)
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter = historyRecyclerViewAdapter
    }

    private fun getHistory() {
        historyVideModel.getHistory().observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    hideProgress()
                    fetchHistoryData(result.data)

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

    private fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    private fun fetchHistoryData(data: List<UserPresentation>) {
        historyList.clear()
        historyList.addAll(data)
        historyRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onHistoryItemSelected(historyItem: UserPresentation) {
        val action =
            MainFlowFragmentDirections.actionMainFlowFragmentToUserInfoFragment(historyItem)
        activityNavController().navigate(action)
    }
}