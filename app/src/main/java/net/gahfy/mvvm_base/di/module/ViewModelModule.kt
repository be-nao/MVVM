package net.gahfy.mvvm_base.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.linecorp.linenovel.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.gahfy.mvvm_base.di.ViewModelKey
import net.gahfy.mvvm_base.ui.dashboard.DashboardViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindMainViewModel(viewModel: DashboardViewModel): ViewModel
}
