package com.example.templateapp.base.extensions

import android.widget.EditText
import androidx.core.widget.doOnTextChanged

fun EditText.onTextChanged(listener: (String) -> Unit) {
    this.doOnTextChanged { text, _, _, _ -> listener.invoke(text.toString()) }
}
