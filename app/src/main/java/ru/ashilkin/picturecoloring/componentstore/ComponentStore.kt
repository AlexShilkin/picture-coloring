package ru.ashilkin.picturecoloring.componentstore

class ComponentStore {

    private val components = mutableMapOf<String, Any>()

    fun add(key: String, component: Any) {
        components[key] = component
    }

    fun remove(key: String) {
        components.remove(key)
    }

    operator fun get(key: String): Any =
        components[key] ?: Throwable("Component of the $key type was not found")

    fun isExist(key: String): Boolean = components.containsKey(key)

    fun findComponent(predicate: (Any) -> Boolean): Any {
        for (component in components.values) {
            if (predicate(component))
                return component
        }
        throw Throwable("Component not found")
    }
}