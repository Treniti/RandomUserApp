package com.treniti.randomuserapp.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.treniti.randomuserapp.data.presentation.UserPresentation
import com.treniti.randomuserapp.databinding.ItemUserBinding
import com.treniti.randomuserapp.presentation.listener.CatalogItemListener
import com.treniti.randomuserapp.utils.viewBinding

class CatalogRecyclerViewAdapter(
    private val data: MutableList<UserPresentation>,
    private val listener: CatalogItemListener
) :
    RecyclerView.Adapter<CatalogRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.viewBinding(ItemUserBinding::inflate))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        val user = data[position]

        holder.binding.fullName.text = user.fullName

        holder.binding.container.setOnClickListener {
            listener.onCatalogItemSelected(user)
        }


        Glide.with(context)
            .load(user.photo)
            .into(holder.binding.photo)
    }

    override fun getItemCount() = data.size
}