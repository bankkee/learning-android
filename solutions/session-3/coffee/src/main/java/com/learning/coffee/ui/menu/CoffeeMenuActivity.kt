package com.learning.coffee.ui.menu

import android.content.Context
import android.content.Intent
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRequest
import com.learning.coffee.R
import com.learning.coffee.common.BaseCoffeeActivity
import com.learning.coffee.databinding.ActivityCoffeeMenuBinding
import com.learning.core.extension.handleResponse
import com.learning.core.extension.singleClick
import com.learning.core.util.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoffeeMenuActivity : BaseCoffeeActivity<ActivityCoffeeMenuBinding>() {

    override fun getViewBinding(): ActivityCoffeeMenuBinding = ActivityCoffeeMenuBinding.inflate(layoutInflater)

    private val coffeeMenuViewModel: CoffeeMenuViewModel by viewModel()

    override fun setUpViews() {
        binding.tvGreeting.text = getString(R.string.coffee_greeting)
        binding.btnRecommend.singleClick {
            coffeeMenuViewModel.getRecommendedCoffee(GetRecommendedCoffeeRequest())
        }
    }

    override fun observeViewModel() {
        observe(coffeeMenuViewModel.recommendedCoffeeResult) { result ->
            handleResponse(result, onSuccess = {
                binding.tvResult.text = getString(R.string.coffee_result, it.name.orEmpty(), it.price ?: 0)
            })
        }
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, CoffeeMenuActivity::class.java)
        }
    }
}
