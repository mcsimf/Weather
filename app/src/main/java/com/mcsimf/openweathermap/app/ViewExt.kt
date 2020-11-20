package com.mcsimf.openweathermap.app

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */

fun EditText.hideSoftKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}


fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}