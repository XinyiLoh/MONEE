package com.example.monee.home.util

import android.app.AlertDialog
import android.graphics.Bitmap
import androidx.core.graphics.scale
import androidx.fragment.app.Fragment
import com.example.monee.R

// Usage: Show an error dialog from fragment
fun Fragment.errorDialog(text: String) {
    AlertDialog.Builder(context)
        .setIcon(R.drawable.ic_error)
        .setTitle("Error")
        .setMessage(text)
        .setPositiveButton("Dismiss", null)
        .show()
}
