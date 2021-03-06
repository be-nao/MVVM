package be_nao.mvvm_base.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import be_nao.mvvm_base.di.ViewModelFactory
import be_nao.mvvm_base.di.ViewModelKey
import be_nao.mvvm_base.ui.dashboard.DashboardViewModel
import be_nao.mvvm_base.ui.home.HomeViewModel
import be_nao.mvvm_base.ui.notifications.NotificationsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(viewModel: NotificationsViewModel): ViewModel
}
