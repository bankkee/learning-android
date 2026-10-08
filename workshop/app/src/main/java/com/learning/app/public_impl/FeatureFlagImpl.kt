package com.learning.app.public_impl

import com.learning.core.featureflag.FeatureFlag

class FeatureFlagImpl : FeatureFlag {

    // TODO(Session 6 · ภารกิจ 1): เปิด flag DESSERT_RECOMMEND โดยเพิ่ม key ลงใน set นี้
    private val enabledFlags = setOf<String>()

    override fun isEnabled(key: String): Boolean = key in enabledFlags
}
