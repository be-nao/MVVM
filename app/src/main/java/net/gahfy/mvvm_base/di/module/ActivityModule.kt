package net.gahfy.mvvm_base.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.gahfy.mvvm_base.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
