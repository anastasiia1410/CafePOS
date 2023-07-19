package com.example.presentation.utils

import android.content.Context
import android.widget.TextView
import com.example.presentation.R
import java.lang.IllegalArgumentException

fun TextView?.inputText(): String {
    return this?.text?.toString() ?: ""
}

