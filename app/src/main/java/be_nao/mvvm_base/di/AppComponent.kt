package be_nao.mvvm_base.di

import be_nao.mvvm_base.data.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import be_nao.mvvm_base.App
import be_nao.mvvm_base.api.RetrofitModule
import be_nao.mvvm_base.di.module.ActivityModule
import be_nao.mvvm_base.di.module.FragmentModule
import be_nao.mvvm_base.di.module.ViewModelModule
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
