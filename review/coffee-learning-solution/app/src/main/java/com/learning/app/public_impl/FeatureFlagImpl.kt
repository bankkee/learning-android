package com.learning.app.public_impl

import com.learning.core.featureflag.FeatureFlag
import com.learning.core.featureflag.FlagConstants

class FeatureFlagImpl : FeatureFlag {

    private val enabledFlags = setOf(
        FlagConstants.DESSERT_RECOMMEND // ผู้เรียนเขียน: Session 6 · ภารกิจ 1
    )

    override fun isEnabled(key: String): Boolean = key in enabledFlags
}
