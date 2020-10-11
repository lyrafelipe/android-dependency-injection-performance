package com.sloydev.dependencyinjectionperformance

import android.app.Application
import com.sloydev.dependencyinjectionperformance.koin.koinJavaModule
import com.sloydev.dependencyinjectionperformance.koin.koinKotlinModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    koinJavaModule,
                    koinKotlinModule
                )
            )
        }
    }
}
