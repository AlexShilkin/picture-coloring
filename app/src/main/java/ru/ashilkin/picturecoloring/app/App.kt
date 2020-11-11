package ru.ashilkin.picturecoloring.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class App : Application() {

    override fun onCreate() {
        super.onCreate()
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