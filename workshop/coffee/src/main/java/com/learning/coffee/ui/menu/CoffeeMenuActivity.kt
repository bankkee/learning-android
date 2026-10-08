package com.learning.coffee.ui.menu

import android.content.Context
import android.content.Intent
import com.learning.coffee.common.BaseCoffeeActivity
import com.learning.coffee.databinding.ActivityCoffeeMenuBinding

class CoffeeMenuActivity : BaseCoffeeActivity<ActivityCoffeeMenuBinding>() {

    override fun getViewBinding(): ActivityCoffeeMenuBinding = ActivityCoffeeMenuBinding.inflate(layoutInflater)

    override fun setUpViews() {
        // TODO(Session 1 · ภารกิจ 2): แสดงคำทักทายที่ tvGreeting และทำให้กด btnRecommend แล้ว tvResult เปลี่ยน
    }

    override fun observeViewModel() {
        // TODO(Session 2 · ภารกิจ 2): observe LiveData จาก ViewModel
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, CoffeeMenuActivity::class.java)
        }
    }
}
