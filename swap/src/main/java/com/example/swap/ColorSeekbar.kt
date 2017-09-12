package com.example.swap

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.util.TypedValue

/**
 * Created by ayush on 9/12/17.
 */
class ColorSeekbar : AppCompatSeekBar {

    lateinit var mpaint: Paint
    lateinit var textRect: Rect

    var textColor: Int = 0xff00000

    var textSize: Float = 18f

    var text: String? = null

    constructor(context: Context) : this(context, null) {
        initialize(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0) {
        initialize(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttributeSet: Int) : super(context, attributeSet, defStyleAttributeSet) {
        initialize(attributeSet)
    }

    fun initialize(attributeSet: AttributeSet?) {

        mpaint = Paint(Paint.LINEAR_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG)
        textRect = Rect()

        if (attributeSet != null) {

            val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialColorPickerTextSeekBar)

            try {

                textColor = typedArray.getColor(R.styleable.MaterialColorPickerTextSeekBar_android_textColor, 0xff00000)
                textSize = typedArray.getDimension(R.styleable.MaterialColorPickerTextSeekBar_android_textSize,
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18f, resources.displayMetrics))
                text = typedArray.getString(R.styleable.MaterialColorPickerTextSeekBar_android_text)

            } finally {
                typedArray.recycle()
            }

        }

        mpaint.color = textColor
        mpaint.typeface = Typeface.DEFAULT
        mpaint.textSize = textSize
        mpaint.textAlign = Paint.Align.CENTER

        mpaint.getTextBounds("255", 0, 3, textRect)
        setPadding(getPaddingLeft(), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (0.6f * textRect.height()), getResources().getDisplayMetrics()).toInt(),
                getPaddingRight(), getPaddingBottom())

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        canvas?.drawText(
//                text?:progress.toString(),
//                (thumb.bounds.left + paddingLeft).toFloat(),
//                (textRect.height() + (paddingTop shr 2)).toFloat(),
//                mpaint
//        )
    }

}