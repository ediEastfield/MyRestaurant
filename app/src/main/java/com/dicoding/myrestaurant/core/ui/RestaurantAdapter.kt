package com.dicoding.myrestaurant.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myrestaurant.BuildConfig
import com.dicoding.myrestaurant.R
import com.dicoding.myrestaurant.core.domain.model.Restaurant
import com.dicoding.myrestaurant.databinding.ItemListRestaurantBinding

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.ListViewHolder>() {

    private var listData = ArrayList<Restaurant>()
    var onItemClick: ((Restaurant) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Restaurant>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_restaurant, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: RestaurantAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListRestaurantBinding.bind(itemView)
        fun bind(data: Restaurant) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${BuildConfig.RESTAURANT_URL}images/medium/${data.pictureId}")
                    .into(ivItemImage)
                tvItemName.text = data.name
                tvItemCity.text = data.city
                tvItemRating.text = data.rating.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}