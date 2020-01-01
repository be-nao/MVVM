package be_nao.mvvm_base.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import be_nao.mvvm_base.ui.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
