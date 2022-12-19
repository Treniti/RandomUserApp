package com.treniti.randomuserapp.presentation.listener

import com.treniti.randomuserapp.data.presentation.UserPresentation

interface CatalogItemListener {
    fun onCatalogItemSelected(catalogItem: UserPresentation)
}