package ru.tinkoff.lab

import android.app.Application
import ru.tinkoff.lab.di.AppComponent
import ru.tinkoff.lab.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}
