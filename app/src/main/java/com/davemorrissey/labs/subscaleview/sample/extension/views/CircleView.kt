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
import android.graphics.Paint.Cap
import android.graphics.Paint.Style
import android.util.AttributeSet
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

class CircleView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) : SubsamplingScaleImageView(context, attr) {

    private var strokeWidth: Int = 0

    private val sCenter = PointF()
    private val vCenter = PointF()
    private val paint = Paint()

    init {
        initialise()
    }

    private fun initialise() {
        val density = resources.displayMetrics.densityDpi.toFloat()
        strokeWidth = (density / 60f).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady) {
            return
        }

        sCenter.set((sWidth / 2).toFloat(), (sHeight / 2).toFloat())
        sourceToViewCoord(sCenter, vCenter)
        val radius = scale * sWidth * 0.25f

        paint.isAntiAlias = true
        paint.style = Style.STROKE
        paint.strokeCap = Cap.ROUND
        paint.strokeWidth = (strokeWidth * 2).toFloat()
        paint.color = Color.BLACK
        canvas.drawCircle(vCenter.x, vCenter.y, radius, paint)
        paint.strokeWidth = strokeWidth.toFloat()
        paint.color = Color.argb(255, 51, 181, 229)
        canvas.drawCircle(vCenter.x, vCenter.y, radius, paint)
    }

}
