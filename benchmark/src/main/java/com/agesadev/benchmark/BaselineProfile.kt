package com.agesadev.benchmark

import android.content.Intent
import android.graphics.Point
import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalBaselineProfilesApi
@RunWith(AndroidJUnit4::class)
class BaselineProfile {


    @get:Rule
    val baselineRule = BaselineProfileRule()

    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)
    }

    @Test
    fun profile() {
        baselineRule.collectBaselineProfile(
            packageName = "com.example.wearoscomposecodelab",
            profileBlock = {
                val intent = Intent()
                intent.action = "com.example.wearoscomposecodelab.MAIN"
                startActivityAndWait(intent)
                scrollDown()
                backWhenIdle()
                backWhenIdle()
            }
        )
    }

    private fun scrollDown() {
        device.waitForIdle()
        val list = device.findObject(By.scrollable(true))
        list.setGestureMargin(device.displayWidth / 5)
        list.drag(Point(list.visibleCenter.x, list.visibleCenter.y / 2))
        device.waitForIdle()
    }

    private fun backWhenIdle() {
        device.waitForIdle()
        device.pressBack()
        device.waitForIdle()
    }


}