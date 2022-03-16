package ru.itis.tinkoff.project.features.main.utils

import android.content.res.Resources

class DisplayExtensions

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
