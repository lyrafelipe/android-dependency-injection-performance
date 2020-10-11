package com.sloydev.dependencyinjectionperformance

import android.content.Context
import org.koin.core.KoinComponent
import org.koin.core.get
import javax.inject.Inject

class InjectionTest : KoinComponent {

    private val kotlinDaggerTest = KotlinDaggerTest()
    private val javaDaggerTest = JavaDaggerTest()

    private val rounds = 100

    fun runTests(context: Context): List<LibraryResult> {
        return listOf(
            koinTest(),
            daggerTest(context)
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

    private fun daggerTest(context: Context): LibraryResult {
        log("Running Dagger...")
        return LibraryResult("Dagger", mapOf(
            Variant.KOTLIN to runTest(
                test = { (context as App).kotlinComponent.inject(kotlinDaggerTest) }
            ),
            Variant.JAVA to runTest(
                test = { (context as App).javaComponent.inject(javaDaggerTest) }
            )
        ))
    }

    class KotlinDaggerTest {
        @Inject
        lateinit var daggerFib8: Fib300
    }

    class JavaDaggerTest {
        @Inject
        lateinit var daggerFib8: FibonacciJava.Fib300
    }
}
