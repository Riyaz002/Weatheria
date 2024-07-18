package com.riyaz.weatheria.home


import android.os.Build
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.rule.GrantPermissionRule
import com.riyaz.presetation.util.TestTag
import com.riyaz.weatheria.MainActivity
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

    private var permissions = if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.POST_NOTIFICATIONS) else arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    @get:Rule(order = 1)
    val permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(*permissions)

    @get:Rule(order = 2)
    val compose = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hilt.inject()
    }

    @Test
    fun checkTemperatureLabel_notEmpty(): Unit = runBlocking{
        compose.waitUntil(10000) {
            compose.onNodeWithTag(TestTag.WEATHER_DESCRIPTION.tag).isDisplayed()
        }
    }

    @Test
    fun checkLocationLabel_notEmpty(): Unit = runBlocking{
        compose.waitUntil(10000) {
            compose.onNodeWithTag(TestTag.LOCATION.tag).isDisplayed()
        }
    }
}