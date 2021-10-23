package com.dicoding.myrestaurant.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myrestaurant.R
import com.dicoding.myrestaurant.core.ui.RestaurantAdapter
import com.dicoding.myrestaurant.databinding.FragmentFavoriteBinding
import com.dicoding.myrestaurant.detail.DetailRestaurantActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val restaurantAdapter = RestaurantAdapter()
            restaurantAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailRestaurantActivity::class.java)
                intent.putExtra(DetailRestaurantActivity.EXTRA_ID, selectedData.restaurantId)
                startActivity(intent)
            }

            favoriteViewModel.favoriteRestaurant.observe(viewLifecycleOwner, { restaurant ->
                restaurantAdapter.setData(restaurant)
                binding.viewEmpty.root.visibility = if (restaurant.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvRestaurant) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = restaurantAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}