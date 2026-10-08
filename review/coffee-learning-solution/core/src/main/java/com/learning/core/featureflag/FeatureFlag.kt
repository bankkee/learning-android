package com.learning.core.featureflag

interface FeatureFlag {
    fun isEnabled(key: String): Boolean
}
