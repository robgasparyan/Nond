package com.company.nond.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.company.nond.MainActivity

fun showLongToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun Fragment.showLoading() {
    activity?.let { (it as MainActivity).showLoading() }
}

fun Fragment.hideLoading() {
    activity?.let { (it as MainActivity).hideLoading() }
}