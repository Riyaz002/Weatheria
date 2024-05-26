package com.riyaz.weatheria

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(com.riyaz.di.AppModule::class)
class HomeScreenKtTest {

    @get:Rule(order = 0)
    val hilt = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hilt.inject()
    }

    @Test
    fun checkWeatherDescriptionText_notEmpty(): Unit = runBlocking {
        compose.awaitIdle()
        compose.onNodeWithTag(com.riyaz.presetation.util.TestTag.WEATHER_DESCRIPTION.tag).assertExists()
    }
}