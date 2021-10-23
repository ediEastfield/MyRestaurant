package com.dicoding.myrestaurant.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.myrestaurant.BuildConfig
import com.dicoding.myrestaurant.R
import com.dicoding.myrestaurant.core.data.Resource
import com.dicoding.myrestaurant.core.domain.model.DetailRestaurant
import com.dicoding.myrestaurant.core.ui.CustomerReviewAdapter
import com.dicoding.myrestaurant.core.ui.MenuAdapter
import com.dicoding.myrestaurant.databinding.ActivityDetailRestaurantBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRestaurantActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private val detailRestaurantViewModel: DetailRestaurantViewModel by viewModels()

    private lateinit var binding: ActivityDetailRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val restaurantId = intent.getStringExtra(EXTRA_ID)
        detailRestaurantViewModel.getDetailRestaurant(restaurantId!!).observe(this, { detail ->
            if (detail != null) {
                when (detail) {
                    is Resource.Loading -> binding.content.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.content.progressBar.visibility = View.GONE
                        showDetailRestaurant(detail.data)
                    }
                    is Resource.Error -> {
                        binding.content.progressBar.visibility = View.GONE
                        binding.content.viewError.root.visibility = View.VISIBLE
                        binding.content.viewError.tvError.text = detail.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        detailRestaurantViewModel.getMenuRestaurant(restaurantId).observe(this, { menuRestaurant ->
            if (menuRestaurant != null) {
                val foodAdapter = MenuAdapter()
                val drinkAdapter = MenuAdapter()

                foodAdapter.setData(menuRestaurant.foods)
                drinkAdapter.setData(menuRestaurant.drinks)

                with(binding.content.rvDrink) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = drinkAdapter
                }

                with(binding.content.rvFoods) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = foodAdapter
                }
            }
        })

        detailRestaurantViewModel.getReviewRestaurant(restaurantId).observe(this, { review ->
            if (review != null) {
                val customerReviewAdapter = CustomerReviewAdapter()
                customerReviewAdapter.setData(review)

                with(binding.content.rvCustomerReviews) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = customerReviewAdapter
                }
            }
        })
    }

    private fun showDetailRestaurant(data: DetailRestaurant?) {
        data?.let {
            supportActionBar?.title = data.name
            Glide.with(this)
                .load("${BuildConfig.RESTAURANT_URL}images/large/${data.pictureId}")
                .into(binding.ivDetailImage)

            binding.content.tvDetailRating.text = data.rating.toString()
            binding.content.tvDetailCity.text = data.city
            binding.content.tvDetailAddress.text = data.address
            binding.content.tvDetailDescription.text = data.description

            var statusFavorite = data.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailRestaurantViewModel.setFavoriteRestaurant(data, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}