package com.davemorrissey.labs.subscaleview.sample.extension.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.davemorrissey.labs.subscaleview.sample.R.drawable


class PinView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) : SubsamplingScaleImageView(context, attr) {

    private val paint = Paint()
    private val vPin = PointF()
    private var sPin: PointF? = null
    private var pin: Bitmap

    init {
        val density = resources.displayMetrics.densityDpi.toFloat()
        val bitmap = BitmapFactory.decodeResource(this.resources, drawable.pushpin_green)
        val w = density / 420f * bitmap.width
        val h = density / 420f * bitmap.height
        pin = Bitmap.createScaledBitmap(bitmap, w.toInt(), h.toInt(), true)
    }

    fun setPin(sPin: PointF) {
        this.sPin = sPin
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady) return

        paint.isAntiAlias = true

        if (sPin != null) {
            sourceToViewCoord(sPin, vPin)
            val vX = vPin.x - pin.width / 2
            val vY = vPin.y - pin.height
            canvas.drawBitmap(pin, vX, vY, paint)
        }

    }

}
