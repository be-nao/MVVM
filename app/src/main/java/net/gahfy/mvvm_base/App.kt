package net.gahfy.mvvm_base

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.gahfy.mvvm_base.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    companion object {
        private lateinit var instance: App
    }

}
