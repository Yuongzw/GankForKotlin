package com.yuong.library_base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

class ToastUtils {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        private val SHORT = Toast.LENGTH_SHORT
        private val LONG = Toast.LENGTH_LONG

        fun init(context: Context) {
            Companion.context = context
        }

        fun showShort(msg: String?) {
            Toast.makeText(context, msg, SHORT).show()
        }

        fun showLone(msg: String?) {
            Toast.makeText(context, msg, LONG).show()
        }
    }


}