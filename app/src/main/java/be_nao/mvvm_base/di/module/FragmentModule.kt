package be_nao.mvvm_base.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import be_nao.mvvm_base.ui.dashboard.DashboardFragment
import be_nao.mvvm_base.ui.home.HomeFragment
import be_nao.mvvm_base.ui.notifications.NotificationsFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeNotifiactionsFragment(): NotificationsFragment
}
