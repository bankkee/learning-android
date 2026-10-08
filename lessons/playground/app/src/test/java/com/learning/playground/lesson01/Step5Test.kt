package com.learning.playground.lesson01

import com.learning.playground.BuildConfig
import org.junit.Assert.assertEquals
import org.junit.Test

// test นี้รันกับ build type debug (testDebugUnitTest)
class Step5Test {
    @Test
    fun `5_1 versionName`() {
        assertEquals("1.1.0", BuildConfig.VERSION_NAME)
    }

    @Test
    fun `5_2 debug applicationId has suffix`() {
        assertEquals("com.learning.playground.debug", BuildConfig.APPLICATION_ID)
    }
}
