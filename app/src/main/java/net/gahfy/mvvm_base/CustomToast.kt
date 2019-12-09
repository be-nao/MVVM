package net.gahfy.mvvm_base

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast

class CustomToast(private val context: Context?) {

    companion object {
        const val FONT_SIZE_SP = 12F
    }

    fun showError(e: Throwable? = null) {
        context ?: return

        val message = ErrorMessage(context).getErrorMessage(e)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showLoginError() {
        context ?: return
        Toast.makeText(context, "ログインエラー", Toast.LENGTH_SHORT).show()
    }

    fun showSuccess(text: CharSequence) {
        context ?: return
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        val view = toast.view
        (view.findViewById<View>(android.R.id.message) as TextView).apply {
            setTextColor(ResourceUtil.getResourceColor(this, R.color.colorAccent))
            textSize = FONT_SIZE_SP
        }
        toast.show()
    }

    fun showPostError(resId: Int) {
        context ?: return
        val toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT)
        val view = toast.view
        (view.findViewById<View>(android.R.id.message) as TextView).apply {
            setTextColor(ResourceUtil.getResourceColor(this, R.color.colorAccent))
            textSize = FONT_SIZE_SP
        }
        toast.show()
    }
}
