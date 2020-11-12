package ru.ashilkin.picturecoloring.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ashilkin.featureimagepicture.dependencies.ImagePictureDependencies
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : ImagePictureDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}