package ru.ashilkin.picturecoloring.componentstore

import android.app.Application

class InjectionManager {

    companion object {
        val instance = InjectionManager()
    }

    private val componentStore = ComponentStore()

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(ActivityLifecycleHelper(componentStore))
    }

    fun <T> bindComponent(owner: ComponentOwner): T {
        return getComponentOrCreate(owner.componentKey(), owner) as T
    }

    inline fun <reified T> findComponent(): T = findComponent { it is T } as T

    fun findComponent(predicate: (Any) -> Boolean): Any = componentStore.findComponent(predicate)


    private fun getComponentOrCreate(key: String, owner: ComponentOwner): Any {
        return when {
            componentStore.isExist(key) -> componentStore[key]
            else -> owner.component().also { componentStore.add(key, it) }
        }
    }
}