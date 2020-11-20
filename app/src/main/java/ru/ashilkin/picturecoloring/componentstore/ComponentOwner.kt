package ru.ashilkin.picturecoloring.componentstore

interface ComponentOwner {

    fun component(): Any

    fun componentKey() = this::class.java.simpleName
}