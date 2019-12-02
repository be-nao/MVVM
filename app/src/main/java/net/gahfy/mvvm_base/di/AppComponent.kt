package net.gahfy.mvvm_base.di

import net.gahfy.mvvm_base.data.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import net.gahfy.mvvm_base.App
import net.gahfy.mvvm_base.api.RetrofitModule
import net.gahfy.mvvm_base.di.module.ActivityModule
import net.gahfy.mvvm_base.di.module.FragmentModule
import net.gahfy.mvvm_base.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        DataModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    override fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
