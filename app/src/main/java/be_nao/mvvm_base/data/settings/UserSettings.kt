package be_nao.mvvm_base.data.settings

interface UserSettings {

    fun getUsername(): String?

    fun deleteAllSettings()

}
