package com.example.swap

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.IntRange
import android.text.InputFilter
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_colorpicker.*

/**
 * Created by ayush on 9/8/17.
 */
class ColorPicker(activity: Activity) : Dialog(activity), SeekBar.OnSeekBarChangeListener {

    val activity: Activity

    var colorPickerCallback: PickerCallback?
    var alpha: Int
    var red: Int
    var green: Int
    var blue: Int
    var withAlpha: Boolean

    init {

        this.activity = activity
        if (activity is PickerCallback) colorPickerCallback = activity
        else colorPickerCallback = null

        alpha = 255
        red = 0
        green = 0
        blue = 0

        withAlpha = false

    }

    constructor(activity: Activity, @IntRange(from = 0, to = 255) alpha: Int,
                @IntRange(from = 0, to = 255) red: Int,
                @IntRange(from = 0, to = 255) green: Int,
                @IntRange(from = 0, to = 255) blue: Int) : this(activity) {

        this.alpha = alpha
        this.red = red
        this.green = green
        this.blue = blue

        this.withAlpha = true

    }

    constructor(activity: Activity,
                @IntRange(from = 0, to = 255) red: Int,
                @IntRange(from = 0, to = 255) green: Int,
                @IntRange(from = 0, to = 255) blue: Int) : this(activity) {

        this.red = red
        this.green = green
        this.blue = blue

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.layout_colorpicker)

        (alphaSeekBar as SeekBar).setOnSeekBarChangeListener(this)
        (redSeekBar as SeekBar).setOnSeekBarChangeListener(this)
        (greenSeekBar as SeekBar).setOnSeekBarChangeListener(this)
        (blueSeekBar as SeekBar).setOnSeekBarChangeListener(this)

        (alphaSeekBar as SeekBar).progress = alpha
        (redSeekBar as SeekBar).progress = red
        (greenSeekBar as SeekBar).progress = green
        (blueSeekBar as SeekBar).progress = blue

        if (!withAlpha) alphaSeekBar.visibility = View.GONE

        textView.text = if (withAlpha) ColorFormatHelper.formatColorValues(red, green, blue)
                        else ColorFormatHelper.formatColorValues(red, green, blue, alpha)

        okColorButton.setOnClickListener {

            v: View? ->
                colorPickerCallback?.onColorSelected(getColor())
                dismiss()
        }

    }

    fun setColorCallback(listener: PickerCallback) {

        colorPickerCallback = listener

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        if (seekBar == null) return

        if (seekBar.id == R.id.alphaSeekBar) alpha = progress
        if (seekBar.id == R.id.redSeekBar) red = progress
        if (seekBar.id == R.id.greenSeekBar) green = progress
        if (seekBar.id == R.id.blueSeekBar) blue = progress

        colorView.setBackgroundColor(getColor())

        textView.text = if (withAlpha) ColorFormatHelper.formatColorValues(red, green, blue, alpha) else ColorFormatHelper.formatColorValues(red, green, blue)

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    fun getColor() = if (withAlpha) Color.argb(alpha, red, green, blue) else Color.rgb(red, green, blue)


}