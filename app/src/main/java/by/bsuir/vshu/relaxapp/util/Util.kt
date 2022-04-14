package by.bsuir.vshu.relaxapp.util

import android.content.res.Resources

object Util {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
    }

}