package com.example.alpha.util

import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import android.graphics.Typeface
import android.R
import android.R.id
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import android.R.attr.font




inline fun ViewGroup.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    snack(resources.getString(messageRes), length, f)
}

inline fun ViewGroup.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    val tv = snack.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    tv.typeface = Typeface.createFromAsset(resources.assets, "font/font_iransansmobile_medium.ttf")
    snack.f()
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}