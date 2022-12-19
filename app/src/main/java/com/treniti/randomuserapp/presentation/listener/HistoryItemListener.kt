package com.treniti.randomuserapp.presentation.listener

import com.treniti.randomuserapp.data.presentation.UserPresentation

interface HistoryItemListener {
    fun onHistoryItemSelected(historyItem: UserPresentation)
}