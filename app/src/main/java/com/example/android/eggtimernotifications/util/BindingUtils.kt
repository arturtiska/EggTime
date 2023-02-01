package com.example.android.eggtimernotifications.util

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Converte milissegundos em mm:ss formatado
 *
 * @param valor, tempo em milissegundos.
 */
@BindingAdapter("elapsedTime")
fun TextView.setElapsedTime(value: Long) {
    val seconds = value / 1000
    text = if (seconds < 60) seconds.toString() else DateUtils.formatElapsedTime(seconds)
}
