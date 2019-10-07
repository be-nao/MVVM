package net.gahfy.mvvm_base.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.gahfy.mvvm_base.ui.dashboard.DashboardFragment
import net.gahfy.mvvm_base.ui.home.HomeFragment
import net.gahfy.mvvm_base.ui.notifications.NotificationsFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeNotifiactionsFragment(): NotificationsFragment
}
