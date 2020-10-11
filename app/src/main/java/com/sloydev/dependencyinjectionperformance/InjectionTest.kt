package com.sloydev.dependencyinjectionperformance

import android.content.Context
import org.koin.core.KoinComponent
import org.koin.core.get

class InjectionTest : KoinComponent {

    private val rounds = 100

    fun runTests(context: Context): List<LibraryResult> {
        return listOf(
            koinTest(),
        )
    }

    private fun runTest(
        test: () -> Unit,
    ): TestResult {
        val testDurations = (1..rounds).map { measureTime { test() } }
        return TestResult(testDurations)
    }

    private fun koinTest(): LibraryResult {
        log("Running Koin...")
        return LibraryResult(
            "Koin", mapOf(
                Variant.KOTLIN to runTest(
                    test = { get<Fib300>() },
                ),
                Variant.JAVA to runTest(
                    test = { get<FibonacciJava.Fib300>() },
                )
            )
        )
    }
}
