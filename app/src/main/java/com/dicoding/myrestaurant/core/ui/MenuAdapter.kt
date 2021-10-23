package com.dicoding.myrestaurant.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myrestaurant.R
import com.dicoding.myrestaurant.core.domain.model.FoodDrink
import com.dicoding.myrestaurant.databinding.ItemListMenuBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ListViewHolder>() {

    private var listData = ArrayList<FoodDrink>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<FoodDrink>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_menu, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMenuBinding.bind(itemView)
        fun bind(data: FoodDrink) {
            with(binding) {
                tvItemName.text = data.name
            }
        }
    }
}