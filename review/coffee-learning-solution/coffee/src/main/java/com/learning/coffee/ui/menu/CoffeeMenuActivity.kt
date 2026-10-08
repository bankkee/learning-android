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

// starter ให้มา: บรรทัดประกาศ class, getViewBinding(), โครงเปล่าของ setUpViews()/observeViewModel() และ companion object
// ที่เหลือผู้เรียนเป็นคนเขียน ตามที่กำกับไว้แต่ละจุด
class CoffeeMenuActivity : BaseCoffeeActivity<ActivityCoffeeMenuBinding>() {

    override fun getViewBinding(): ActivityCoffeeMenuBinding = ActivityCoffeeMenuBinding.inflate(layoutInflater)

    private val coffeeMenuViewModel: CoffeeMenuViewModel by viewModel() // Session 2 · ภารกิจ 2
    private val featureFlag: FeatureFlag by inject() // Session 6 · ภารกิจ 1
    private var currentCoffeeName: String? = null // Session 4 · ภารกิจ 1
    // ผู้เรียนเขียน: Session 5 · ภารกิจ 4
    private val coffeeMenuAdapter by lazy {
        CoffeeMenuAdapter(onItemClick = { openDetail(it.name.orEmpty()) })
    }

    override fun setUpViews() {
        // ผู้เรียนเขียน: Session 1 · ภารกิจ 2
        binding.tvGreeting.text = getString(R.string.coffee_greeting)
        // ผู้เรียนเขียน: Session 1 · ภารกิจ 2 (setOnClickListener ตั้งข้อความตรง ๆ)
        // → Session 2 · ภารกิจ 2 เปลี่ยนเป็น singleClick + เรียก ViewModel → Session 3 · ภารกิจ 3 เปลี่ยนเป็นเรียก API
        binding.btnRecommend.singleClick {
            coffeeMenuViewModel.getRecommendedCoffee(GetRecommendedCoffeeRequest())
        }
        // ผู้เรียนเขียน: Session 4 · ภารกิจ 1
        binding.tvResult.singleClick {
            currentCoffeeName?.let { openDetail(it) }
        }
        // ผู้เรียนเขียน: Session 5 · ภารกิจ 4
        binding.rvMenu.layoutManager = LinearLayoutManager(this)
        binding.rvMenu.adapter = coffeeMenuAdapter

        // ผู้เรียนเขียน: Session 6 · ภารกิจ 1
        binding.btnDessert.isVisible = featureFlag.isEnabled(FlagConstants.DESSERT_RECOMMEND)
        // ผู้เรียนเขียน: Session 6 · ภารกิจ 3
        binding.btnDessert.singleClick {
            coffeeMenuViewModel.getRecommendedDessert(GetRecommendedDessertRequest())
        }

        // ผู้เรียนเขียน: Session 5 · ภารกิจ 4
        coffeeMenuViewModel.getCoffeeMenu(GetCoffeeMenuRequest())
    }

    override fun observeViewModel() {
        // ผู้เรียนเขียน: Session 2 · ภารกิจ 2 (observe LiveData<String>) → Session 3 · ภารกิจ 3 เปลี่ยนเป็น handleResponse
        observe(coffeeMenuViewModel.recommendedCoffeeResult) { result ->
            handleResponse(result, onSuccess = {
                currentCoffeeName = it.name // Session 4 · ภารกิจ 1
                binding.tvResult.text = getString(R.string.coffee_result, it.name.orEmpty(), it.price ?: 0)
            })
        }

        // ผู้เรียนเขียน: Session 5 · ภารกิจ 4
        observe(coffeeMenuViewModel.coffeeMenuResult) { result ->
            handleResponse(result, onSuccess = {
                coffeeMenuAdapter.updateItems(it.items.orEmpty())
            })
        }

        // ผู้เรียนเขียน: Session 6 · ภารกิจ 3
        observe(coffeeMenuViewModel.recommendedDessertResult) { result ->
            handleResponse(result, onSuccess = {
                binding.tvDessert.text = getString(R.string.coffee_result, it.name.orEmpty(), it.price ?: 0)
            })
        }
    }

    // ผู้เรียนเขียน: Session 4 · ภารกิจ 1
    private fun openDetail(coffeeName: String) {
        startActivity(CoffeeDetailActivity.newInstance(this, coffeeName))
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, CoffeeMenuActivity::class.java)
        }
    }
}
