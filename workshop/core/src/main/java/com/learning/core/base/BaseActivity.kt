package com.learning.core.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.learning.core.R
import com.learning.core.di.ModuleInject

abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: AlertDialog? = null

    open fun getDi(): ModuleInject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        init()
        observeViewModel()
        setUpViews()
    }

    @CallSuper
    open fun init() {
        getDi()?.let {
            it.dropFeature()
            it.injectFeature()
        }
    }

    open fun observeViewModel() {
        //do nothing
    }

    open fun setUpViews() {
        //do nothing
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        logLifecycle("onPause")
        super.onPause()
    }

    override fun onStop() {
        logLifecycle("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        logLifecycle("onDestroy")
        loadingDialog?.dismiss()
        loadingDialog = null
        super.onDestroy()
    }

    fun showLoading(show: Boolean) {
        if (show) {
            val dialog = loadingDialog ?: AlertDialog.Builder(this)
                .setView(ProgressBar(this))
                .setCancelable(false)
                .create()
                .also {
                    it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    loadingDialog = it
                }
            dialog.show()
        } else {
            loadingDialog?.dismiss()
        }
    }

    fun showAlertDialog(message: String, block: () -> Unit = {}) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.common_ok) { _, _ -> block() }
            .show()
    }

    private fun logLifecycle(event: String) {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} $event")
    }

    companion object {
        const val LIFECYCLE_TAG = "Lifecycle"
    }
}
