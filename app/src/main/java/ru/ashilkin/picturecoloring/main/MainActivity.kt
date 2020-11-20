package ru.ashilkin.picturecoloring.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.ashilkin.picturecoloring.R
import ru.ashilkin.picturecoloring.main.di.DaggerMainComponent
import ru.ashilkin.picturecoloring.main.di.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigationHolder: NavigatorHolder

    private val navigator: AppNavigator = AppNavigator(this, R.id.container)



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    private fun createComponent(): MainComponent{
        return DaggerMainComponent.factory()
            .create()
    }

}