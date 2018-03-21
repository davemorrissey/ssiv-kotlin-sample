package com.davemorrissey.labs.subscaleview.sample.extension.views

import android.content.Context
import android.graphics.*
import android.graphics.Paint.Cap
import android.graphics.Paint.Style
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

class FreehandView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null) : SubsamplingScaleImageView(context, attr), OnTouchListener {

    private val paint = Paint()
    private val vPath = Path()
    private val vPoint = PointF()
    private var vPrev = PointF()
    private var vPrevious: PointF? = null
    private var sStart: PointF? = null
    private var drawing = false

    private var strokeWidth: Int = 0

    private val sPoints: MutableList<PointF> = mutableListOf()

    init {
        initialise()
    }

    private fun initialise() {
        setOnTouchListener(this)
        val density = resources.displayMetrics.densityDpi.toFloat()
        strokeWidth = (density / 60f).toInt()
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (sPoints.isNotEmpty() && !drawing) {
            return super.onTouchEvent(event)
        }
        var consumed = false
        val touchCount = event.pointerCount
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> if (event.actionIndex == 0) {
                sStart = viewToSourceCoord(event.x, event.y)
                vPrevious = PointF(event.x, event.y)
            } else {
                sStart = null
                vPrevious = null
            }
            MotionEvent.ACTION_MOVE -> {
                val sCurrentF = viewToSourceCoord(event.x, event.y)
                if (sCurrentF != null) {
                    val sCurrent = PointF(sCurrentF.x, sCurrentF.y)

                    val sStart = this.sStart
                    val vPrevious = this.vPrevious
                    if (touchCount == 1 && sStart != null && vPrevious != null) {
                        val vDX = Math.abs(event.x - vPrevious.x)
                        val vDY = Math.abs(event.y - vPrevious.y)
                        if (vDX >= strokeWidth || vDY >= strokeWidth) {
                            if (sPoints.isEmpty()) {
                                sPoints.add(sStart)
                            }
                            sPoints.add(sCurrent)
                            vPrevious.x = event.x
                            vPrevious.y = event.y
                            drawing = true
                        }
                        consumed = true
                        invalidate()
                    } else if (touchCount == 1) {
                        // Consume all one touch drags to prevent odd panning effects handled by the superclass.
                        consumed = true
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                invalidate()
                drawing = false
                vPrevious = null
                sStart = null
            }
        }
        // Use parent to handle pinch and two-finger pan.
        return consumed || super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Don't draw anything before image is ready.
        if (!isReady) {
            return
        }

        paint.isAntiAlias = true

        if (sPoints.size >= 2) {
            vPath.reset()
            sourceToViewCoord(sPoints[0].x, sPoints[0].y, vPrev)
            vPath.moveTo(vPrev.x, vPrev.y)
            for (i in 1 until sPoints.size) {
                sourceToViewCoord(sPoints[i].x, sPoints[i].y, vPoint)
                vPath.quadTo(vPrev.x, vPrev.y, (vPoint.x + vPrev.x) / 2, (vPoint.y + vPrev.y) / 2)
                vPrev = vPoint
            }
            paint.style = Style.STROKE
            paint.strokeCap = Cap.ROUND
            paint.strokeWidth = (strokeWidth * 2).toFloat()
            paint.color = Color.BLACK
            canvas.drawPath(vPath, paint)
            paint.strokeWidth = strokeWidth.toFloat()
            paint.color = Color.argb(255, 38, 166, 154)
            canvas.drawPath(vPath, paint)
        }

    }

    fun reset() {
        this.sPoints.clear()
        invalidate()
    }

}
