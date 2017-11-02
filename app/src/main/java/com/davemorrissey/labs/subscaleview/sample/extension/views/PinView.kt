/*
Copyright 2014 David Morrissey

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

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
    private var pin: Bitmap? = null

    init {
        initialise()
    }

    fun setPin(sPin: PointF) {
        this.sPin = sPin
        initialise()
        invalidate()
    }

    private fun initialise() {
        val density = resources.displayMetrics.densityDpi.toFloat()
        pin = BitmapFactory.decodeResource(this.resources, drawable.pushpin_blue)
        val w = density / 420f * pin!!.width
        val h = density / 420f * pin!!.height
        pin = Bitmap.createScaledBitmap(pin!!, w.toInt(), h.toInt(), true)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady) {
            return
        }

        paint.isAntiAlias = true

        if (sPin != null && pin != null) {
            sourceToViewCoord(sPin!!, vPin)
            val vX = vPin.x - pin!!.width / 2
            val vY = vPin.y - pin!!.height
            canvas.drawBitmap(pin!!, vX, vY, paint)
        }

    }

}
