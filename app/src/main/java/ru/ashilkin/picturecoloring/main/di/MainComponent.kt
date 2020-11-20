package ru.ashilkin.picturecoloring.main.di

import dagger.Component
import ru.ashilkin.picturecoloring.main.MainActivity
import ru.ashilkin.picturecoloring.main.dependencies.MainDependencies

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainModule::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}