package net.gahfy.mvvm_base.data.settings

interface UserSettings {

    fun isLoggedIn(): Boolean

    fun getUsername(): String?

    fun getAccessToken(): String?

    fun getRefreshToken(): String?

    fun getAccessTokenExpiresIn(): Int?

    fun putLoginInformation(
        accessToken: String,
        refreshToken: String,
        expiresIn: Int,
        username: String
    )

    fun deleteAllSettings()

    fun getValidationCode(): String?

    fun putValidationCode(code: String)

    fun setNeedRegister(isNeedRegister: Boolean)

    fun isNeedRegister(): Boolean

    fun deleteNeedRegister()

    fun deleteValidationCode()

    fun getEmailLoginRegisterCode(): String?

    fun putEmailLoginRegisterCode(registerCode: String)

    fun deleteEmailLoginRegisterCode()

    fun getEmailLoginDeviceCode(): String?

    fun putEmailLoginDeviceCode(deviceCode: String)

    fun deleteEmailLoginDeviceCode()

    fun getChangeEmailAddressConfirmCode(): String?

    fun putChangeEmailAddressConfirmCode(confirmCode: String)

    fun deleteChangeEmailAddressConfirmCode()

    fun isSelectedNovelType(): Boolean

    fun isBookshelfShowed(): Boolean

    fun setBookshelfShowed(bookshelfShowed: Boolean?)
}
