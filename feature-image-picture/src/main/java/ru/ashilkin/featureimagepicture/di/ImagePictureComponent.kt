package ru.ashilkin.featureimagepicture.di

import dagger.Component
import ru.ashilkin.featureimagepicture.dependencies.ImagePictureDependencies
import ru.ashilkin.featureimagepicture.internal.ImagePictureFragment

@ImagePictureScope
@Component(
    dependencies = [ImagePictureDependencies::class],
    modules = [ImagePictureModule::class]
)
interface ImagePictureComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ImagePictureDependencies): ImagePictureComponent
    }

    fun inject(fragment: ImagePictureFragment)
}