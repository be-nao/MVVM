package net.gahfy.mvvm_base

import android.os.Build
import android.view.View

object ResourceUtil {
    fun getResourceColor(view: View, colorResource: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.context.getColor(colorResource)
        } else {
            @Suppress("DEPRECATION")
            view.resources.getColor(colorResource)
        }
    }
}
