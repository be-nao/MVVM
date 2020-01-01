package be_nao.mvvm_base.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.security.KeyChainException
import android.util.Base64
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain
import com.facebook.crypto.Crypto
import com.facebook.crypto.CryptoConfig
import com.facebook.crypto.Entity
import com.facebook.crypto.exception.CryptoInitializationException
import com.facebook.crypto.util.SystemNativeCryptoLibrary
import timber.log.Timber
import java.io.IOException

class SharedPreferenceUtils(context: Context) {

    companion object {
        private val CRYPTO_CONFIG = CryptoConfig.KEY_256
        private val CHARSET = Charsets.UTF_8
    }

    private val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val crypto: Crypto = Crypto(
        SharedPrefsBackedKeyChain(context,
            CRYPTO_CONFIG
        ),
        SystemNativeCryptoLibrary(),
        CRYPTO_CONFIG
    )

    fun clear() {
        preference.edit().clear().apply()
    }

    fun putString(key: String, value: String?) {
        if (value == null) {
            preference.edit().remove(key).apply()
            return
        }
        preference.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        if (!preference.contains(key)) {
            return null
        }
        return preference.getString(key, null)
    }

    fun putInt(key: String, value: Int?) {
        if (value == null) {
            preference.edit().remove(key).apply()
            return
        }
        preference.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int? {
        if (!preference.contains(key)) {
            return null
        }
        return preference.getInt(key, 0)
    }

    fun putBoolean(key: String, value: Boolean?) {
        if (value == null) {
            preference.edit().remove(key).apply()
            return
        }
        preference.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean? {
        if (!preference.contains(key)) {
            return null
        }
        return preference.getBoolean(key, false)
    }

    /**
     * Get string from preferences. Value will be decrypted by Conceal.
     *
     * @param key The name of the preference to modify.
     */
    fun getEncryptedString(key: String): String? {
        // Return defaultValue if preferences does not contain the key.
        if (!preference.contains(key)) {
            return null
        }

        // Get crypto encrypted base64 value from preferences.
        val encryptedBase64Value = preference.getString(key, null) ?: return null
        // Return null if value is null.

        try {
            // Decrypt.
            val entity = Entity.create(key)
            val encryptedBytes = Base64.decode(encryptedBase64Value, Base64.DEFAULT)
            val decryptedBytes: ByteArray
            decryptedBytes = if (crypto.isAvailable) {
                crypto.decrypt(encryptedBytes, entity)
            } else {
                encryptedBytes
            }

            // Return decrypted value.
            return String(decryptedBytes,
                CHARSET
            )
        } catch (e: KeyChainException) {
            Timber.e(e, "KeyChainException")
        } catch (e: CryptoInitializationException) {
            Timber.e(e, "CryptoInitializationException")
        } catch (e: IOException) {
            Timber.e(e, "IOException")
        }

        return null
    }

    /**
     * Put string to preferences. Stored value will be encrypted by Conceal.
     *
     * @param key The name of the preference to modify.
     * @param value The set of new values for the preference.
     */
    fun putEncryptedString(key: String, value: String?) {
        // Put null if value is null.
        if (value == null) {
            preference.edit().remove(key).apply()
            return
        }

        try {
            // Encrypt.
            val entity = Entity.create(key)
            val decryptedBytes = value.toByteArray(CHARSET)
            val encryptedBytes: ByteArray
            encryptedBytes = if (crypto.isAvailable) {
                crypto.encrypt(decryptedBytes, entity)
            } else {
                decryptedBytes
            }
            val encryptedBase64Value = Base64.encodeToString(encryptedBytes, Base64.DEFAULT)

            // Put crypto encrypted base64 value to preferences.
            preference.edit().putString(key, encryptedBase64Value).apply()
        } catch (e: KeyChainException) {
            Timber.e(e, "KeyChainException")
        } catch (e: CryptoInitializationException) {
            Timber.e(e, "CryptoInitializationException")
        } catch (e: IOException) {
            Timber.e(e, "IOException")
        }
    }
}
