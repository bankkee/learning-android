package com.learning.coffee.ui.menu

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.apilayer.repository.coffee.GetCoffeeMenuRequest
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRequest
import com.learning.apilayer.repository.dessert.GetRecommendedDessertRequest
import com.learning.coffee.R
import com.learning.coffee.common.BaseCoffeeActivity
import com.learning.coffee.databinding.ActivityCoffeeMenuBinding
import com.learning.coffee.ui.detail.CoffeeDetailActivity
import com.learning.core.extension.handleResponse
import com.learning.core.extension.singleClick
import com.learning.core.featureflag.FeatureFlag
import com.learning.core.featureflag.FlagConstants
import com.learning.core.util.observe
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoffeeMenuActivity : BaseCoffeeActivity<ActivityCoffeeMenuBinding>() {

    override fun getViewBinding(): ActivityCoffeeMenuBinding = ActivityCoffeeMenuBinding.inflate(layoutInflater)

    private val coffeeMenuViewModel: CoffeeMenuViewModel by viewModel()
    private val featureFlag: FeatureFlag by inject()
    private var currentCoffeeName: String? = null
    private val coffeeMenuAdapter by lazy {
        CoffeeMenuAdapter(onItemClick = { openDetail(it.name.orEmpty()) })
    }

    override fun setUpViews() {
        binding.tvGreeting.text = getString(R.string.coffee_greeting)
        binding.btnRecommend.singleClick {
            coffeeMenuViewModel.getRecommendedCoffee(GetRecommendedCoffeeRequest())
        }
        binding.tvResult.singleClick {
            currentCoffeeName?.let { openDetail(it) }
        }
        binding.rvMenu.layoutManager = LinearLayoutManager(this)
        binding.rvMenu.adapter = coffeeMenuAdapter

        binding.btnDessert.isVisible = featureFlag.isEnabled(FlagConstants.DESSERT_RECOMMEND)
        binding.btnDessert.singleClick {
            coffeeMenuViewModel.getRecommendedDessert(GetRecommendedDessertRequest())
        }

        coffeeMenuViewModel.getCoffeeMenu(GetCoffeeMenuRequest())
    }

    override fun observeViewModel() {
        observe(coffeeMenuViewModel.recommendedCoffeeResult) { result ->
            handleResponse(result, onSuccess = {
                currentCoffeeName = it.name
                binding.tvResult.text = getString(R.string.coffee_result, it.name.orEmpty(), it.price ?: 0)
            })
        }

        observe(coffeeMenuViewModel.coffeeMenuResult) { result ->
            handleResponse(result, onSuccess = {
                coffeeMenuAdapter.updateItems(it.items.orEmpty())
            })
        }

        observe(coffeeMenuViewModel.recommendedDessertResult) { result ->
            handleResponse(result, onSuccess = {
                binding.tvDessert.text = getString(R.string.coffee_result, it.name.orEmpty(), it.price ?: 0)
            })
        }
    }

    private fun openDetail(coffeeName: String) {
        startActivity(CoffeeDetailActivity.newInstance(this, coffeeName))
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, CoffeeMenuActivity::class.java)
        }
    }
}
