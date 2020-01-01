package be_nao.mvvm_base.data.settings.impl

import android.content.Context
import be_nao.mvvm_base.util.SharedPreferenceUtils
import be_nao.mvvm_base.data.settings.UserSettings

class SharedPreferenceUserSettings(context: Context) : UserSettings {

    enum class Key(val keyName: String) {
        // When save on SharedPreference, use `keyName` to key name.
        // If you don't want users to know key/value, you should set `keyName` to meaningless label.
        USERNAME("username")
    }

    private val preference = SharedPreferenceUtils(context)

    override fun deleteAllSettings() {
        preference.clear()
    }

    override fun getUsername(): String? {
        return getString(Key.USERNAME)
    }

    private fun getString(key: Key): String? {
        return preference.getString(key.keyName)
    }

    private fun putString(key: Key, value: String?) {
        preference.putString(key.keyName, value)
    }

    private fun getInt(key: Key): Int? {
        return preference.getInt(key.keyName)
    }

    private fun putInt(key: Key, value: Int?) {
        preference.putInt(key.keyName, value)
    }

    private fun getBoolean(key: Key): Boolean {
        return preference.getBoolean(key.keyName) ?: false
    }

    private fun putBoolean(key: Key, value: Boolean?) {
        preference.putBoolean(key.keyName, value)
    }

    private fun getEncryptedString(key: Key): String? {
        return preference.getEncryptedString(key.keyName)
    }

    private fun putEncryptedString(key: Key, value: String?) {
        preference.putEncryptedString(key.keyName, value)
    }
}
