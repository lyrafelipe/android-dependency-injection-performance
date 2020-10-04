package com.sloydev.dependencyinjectionperformance

import android.app.Application
import com.sloydev.dependencyinjectionperformance.dagger2.DaggerJavaDaggerComponent
import com.sloydev.dependencyinjectionperformance.dagger2.DaggerKotlinDaggerComponent
import com.sloydev.dependencyinjectionperformance.dagger2.JavaDaggerComponent
import com.sloydev.dependencyinjectionperformance.dagger2.KotlinDaggerComponent
import com.sloydev.dependencyinjectionperformance.koin.koinJavaModule
import com.sloydev.dependencyinjectionperformance.koin.koinKotlinModule
import org.koin.core.context.startKoin

class App : Application() {
    lateinit var kotlinComponent: KotlinDaggerComponent
    lateinit var javaComponent: JavaDaggerComponent

    override fun onCreate() {
        super.onCreate()

        kotlinComponent = DaggerKotlinDaggerComponent.create()
        javaComponent = DaggerJavaDaggerComponent.create()

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
