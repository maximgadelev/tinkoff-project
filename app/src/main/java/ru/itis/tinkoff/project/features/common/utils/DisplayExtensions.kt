package ru.itis.tinkoff.project.features.common.utils

import android.content.res.Resources

class DisplayExtensions

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
