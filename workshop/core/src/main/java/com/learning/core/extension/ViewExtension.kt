package com.learning.core.extension

import android.os.SystemClock
import android.view.View

private const val SINGLE_CLICK_INTERVAL = 600L

/** เหมือน setOnClickListener แต่กันการกดรัว: กดซ้ำภายใน 0.6 วินาทีจะถูกเมิน */
fun View.singleClick(action: (View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        val now = SystemClock.elapsedRealtime()
        if (now - lastClickTime >= SINGLE_CLICK_INTERVAL) {
            lastClickTime = now
            action(it)
        }
    }
}
