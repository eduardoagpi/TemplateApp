package com.example.templateapp.base.extensions

import android.view.View

fun View.showOrHide(showOrHide: Boolean) {
    this.visibility = if (showOrHide) View.VISIBLE else View.GONE
}
