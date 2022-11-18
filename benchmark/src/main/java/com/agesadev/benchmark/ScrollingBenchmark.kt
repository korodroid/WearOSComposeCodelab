package com.agesadev.benchmark

import android.content.Intent
import android.graphics.Point
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@LargeTest
@RunWith(Parameterized::class)
class ScrollingBenchmark(
    private val compilationMode: CompilationMode
) {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()


    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.example.wearoscomposecodelab",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = compilationMode,
        iterations = 10,
        startupMode = StartupMode.COLD,
    ) {
        val list = device.findObject(By.desc("ScalingLazyColumn"))

        // Setting a gesture margin is important otherwise gesture nav is triggered.
        list.setGestureMargin(device.displayWidth / 5)

        repeat(5) {
            list.drag(Point(list.visibleCenter.x, list.visibleCenter.y / 3))
            device.waitForIdle()
        }
    }


    companion object {
        @Parameterized.Parameters(name = "compilation={0}")
        @JvmStatic
        fun parameters() = listOf(CompilationMode.None(), CompilationMode.Partial())
    }


}