package be_nao.mvvm_base.data

import dagger.Module
import dagger.Provides
import be_nao.mvvm_base.App
import be_nao.mvvm_base.data.settings.UserSettings
import be_nao.mvvm_base.data.settings.impl.SharedPreferenceUserSettings
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideUserSettings(app: App): UserSettings {
        return SharedPreferenceUserSettings(app)
    }
}
