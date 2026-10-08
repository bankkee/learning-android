package com.learning.playground

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// ของกลางของ playground ไม่ใช่เนื้อหาของบทใด ไม่ต้องอ่าน
// กันพื้นที่ของ status bar, แถบนำทาง และแป้นพิมพ์ให้ทุกหน้าจอ เพื่อให้ layout ของแต่ละบทไม่ต้องจัดการเอง

class PlaygroundApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                val content = activity.findViewById<View>(android.R.id.content)
                ViewCompat.setOnApplyWindowInsetsListener(content) { view, insets ->
                    val bars = insets.getInsets(
                        WindowInsetsCompat.Type.systemBars() or
                            WindowInsetsCompat.Type.displayCutout() or
                            WindowInsetsCompat.Type.ime()
                    )
                    view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
                    WindowInsetsCompat.CONSUMED
                }
            }

            override fun onActivityStarted(activity: Activity) = Unit
            override fun onActivityResumed(activity: Activity) = Unit
            override fun onActivityPaused(activity: Activity) = Unit
            override fun onActivityStopped(activity: Activity) = Unit
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
            override fun onActivityDestroyed(activity: Activity) = Unit
        })
    }
}
