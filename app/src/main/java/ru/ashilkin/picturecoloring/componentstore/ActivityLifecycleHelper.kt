package ru.ashilkin.picturecoloring.componentstore

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivityLifecycleHelper(private val componentStore: ComponentStore) :
    Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is ComponentOwner && activity.isFinishing) {
            componentStore.remove(activity.componentKey())
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity is AppCompatActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                FragmentLifecycleHelper(componentStore),
                true
            )
        }
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }
}