package com.learning.core.base

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.viewbinding.ViewBinding

abstract class NewBaseActivity<VBinding : ViewBinding> : BaseActivity() {

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding?

    override fun init() {
        binding = getViewBinding()!!
        setContentView(binding.root)
        updateInset()
        super.init()
    }

    /** กันเนื้อหาไปซ้อนใต้ status bar / navigation bar โดยบวกระยะของแถบระบบเข้ากับ padding เดิม */
    open fun updateInset() {
        val root = binding.root
        val initialTop = root.paddingTop
        val initialBottom = root.paddingBottom
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.updatePadding(
                top = initialTop + bars.top,
                bottom = initialBottom + bars.bottom,
            )
            insets
        }
    }
}
