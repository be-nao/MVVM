

import android.content.Context
import net.gahfy.mvvm_base.SharedPreferenceUtils
import net.gahfy.mvvm_base.data.settings.UserSettings

class SharedPreferenceUserSettings(context: Context) : UserSettings {

    enum class Key(val keyName: String) {
        // When save on SharedPreference, use `keyName` to key name.
        // If you don't want users to know key/value, you should set `keyName` to meaningless label.
        ACCESS_TOKEN("a"),
        REFRESH_TOKEN("b"),
        EXPIRES_IN("expires_in"),
        REGISTER_CODE("c"),
        CHANGE_EMAIL_ADDRESS_CONFIRM_CODE("d"),
        DEVICE_CODE("e"),
        VALIDATION_CODE("f"),
        NOVEL_TYPE("novel_type"),
        EPISODE_FONTSIZE("episode_fontsize"),
        EPISODE_COLOR("episode_color"),
        EPISODE_FONT("episode_font"),
        CHECKED_TERM_OF_USE("checked_term_of_use"),
        BOOKSHELF_SHOWED("bookshelf_showed"),
        USERNAME("username"),
        NEED_REGISTER("need_register")
    }

    private val preference = SharedPreferenceUtils(context)

    override fun deleteAllSettings() {
        preference.clear()
    }

    override fun isLoggedIn(): Boolean {
        return getAccessToken() != null
    }

    override fun getUsername(): String? {
        return getString(Key.USERNAME)
    }

    override fun getAccessToken(): String? {
        return getEncryptedString(Key.ACCESS_TOKEN)
    }

    override fun getRefreshToken(): String? {
        return getEncryptedString(Key.REFRESH_TOKEN)
    }

    override fun getAccessTokenExpiresIn(): Int? {
        return getInt(Key.EXPIRES_IN)
    }

    override fun setNeedRegister(isNeedRegister: Boolean) {
        putBoolean(Key.NEED_REGISTER, isNeedRegister)
    }

    override fun isNeedRegister(): Boolean {
        return getBoolean(Key.NEED_REGISTER)
    }

    override fun deleteNeedRegister() {
        putBoolean(Key.NEED_REGISTER, null)
    }

    override fun putLoginInformation(accessToken: String, refreshToken: String, expiresIn: Int, username: String) {
        putEncryptedString(Key.ACCESS_TOKEN, accessToken)
        putEncryptedString(Key.REFRESH_TOKEN, refreshToken)
        putInt(Key.EXPIRES_IN, expiresIn)
        putString(Key.USERNAME, username)
    }

    override fun getValidationCode(): String? {
        return getEncryptedString(Key.VALIDATION_CODE)
    }

    override fun putValidationCode(code: String) {
        return putEncryptedString(Key.VALIDATION_CODE, code)
    }

    override fun deleteValidationCode() {
        putEncryptedString(Key.VALIDATION_CODE, null)
    }

    override fun getEmailLoginRegisterCode(): String? {
        return getString(Key.REGISTER_CODE)
    }

    override fun putEmailLoginRegisterCode(registerCode: String) {
        putString(Key.REGISTER_CODE, registerCode)
    }

    override fun deleteEmailLoginRegisterCode() {
        putString(Key.REGISTER_CODE, null)
    }

    override fun getEmailLoginDeviceCode(): String? {
        return getString(Key.DEVICE_CODE)
    }

    override fun putEmailLoginDeviceCode(deviceCode: String) {
        putString(Key.DEVICE_CODE, deviceCode)
    }

    override fun deleteEmailLoginDeviceCode() {
        putString(Key.DEVICE_CODE, null)
    }

    override fun getChangeEmailAddressConfirmCode(): String? {
        return getString(Key.CHANGE_EMAIL_ADDRESS_CONFIRM_CODE)
    }

    override fun putChangeEmailAddressConfirmCode(confirmCode: String) {
        putString(Key.CHANGE_EMAIL_ADDRESS_CONFIRM_CODE, confirmCode)
    }

    override fun deleteChangeEmailAddressConfirmCode() {
        putString(Key.CHANGE_EMAIL_ADDRESS_CONFIRM_CODE, null)
    }

    override fun isSelectedNovelType(): Boolean = getInt(Key.NOVEL_TYPE) != null

    override fun isBookshelfShowed() = getBoolean(Key.BOOKSHELF_SHOWED)

    override fun setBookshelfShowed(bookshelfShowed: Boolean?) {
        putBoolean(Key.BOOKSHELF_SHOWED, bookshelfShowed)
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
