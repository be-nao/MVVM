package net.gahfy.mvvm_base.data

import SharedPreferenceUserSettings
import dagger.Module
import dagger.Provides
import net.gahfy.mvvm_base.App
import net.gahfy.mvvm_base.data.settings.UserSettings
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideUserSettings(app: App): UserSettings {
        return SharedPreferenceUserSettings(app)
    }
}
