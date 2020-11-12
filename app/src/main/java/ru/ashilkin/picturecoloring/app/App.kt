package ru.ashilkin.picturecoloring.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import ru.ashilkin.picturecoloring.app.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.factory()
            .create(this)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        try {
            MultiDex.install(base)
        } catch (multiDexException: RuntimeException) {
            multiDexException.printStackTrace()
        }
    }

}