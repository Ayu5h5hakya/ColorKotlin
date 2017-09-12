package com.example.swap

import android.support.annotation.ColorInt

/**
 * Created by ayush on 9/8/17.
 */
interface PickerCallback {

    fun onColorSelected(@ColorInt color : Int)

}