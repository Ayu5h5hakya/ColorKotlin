package com.example.swap

import android.support.annotation.IntRange

/**
 * Created by ayush on 9/8/17.
 */
class ColorFormatHelper {

    companion object {

        fun assertColorValueInRange(@IntRange(from = 0, to = 255) colorValue : Int) = if ((colorValue >= 0) && (colorValue <=255)) colorValue else 0

        fun formatColorValues(
            @IntRange(from = 0, to = 255) red : Int,
            @IntRange(from = 0, to = 255) green : Int,
            @IntRange(from = 0, to = 255) blue : Int,
            @IntRange(from = 0, to = 255) alpha : Int = 255) = String.format("%02X%02X%02X%02X",
                assertColorValueInRange(alpha),
                assertColorValueInRange(red),
                assertColorValueInRange(green),
                assertColorValueInRange(blue))

    }

}