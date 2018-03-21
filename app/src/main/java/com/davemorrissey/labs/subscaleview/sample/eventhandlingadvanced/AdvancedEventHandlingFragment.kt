package com.davemorrissey.labs.subscaleview.sample.eventhandlingadvanced

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesFragment
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

import kotlinx.android.synthetic.main.pages_activity.imageView

class AdvancedEventHandlingFragment : AbstractPagesFragment(advancedevent_title, pages_activity, Arrays.asList(
        Page(advancedevent_p1_subtitle, advancedevent_p1_text),
        Page(advancedevent_p2_subtitle, advancedevent_p2_text),
        Page(advancedevent_p3_subtitle, advancedevent_p3_text),
        Page(advancedevent_p4_subtitle, advancedevent_p4_text),
        Page(advancedevent_p5_subtitle, advancedevent_p5_text)
)) {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gestureDetector = GestureDetector(activity, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                imageView.viewToSourceCoord(e.x, e.y)?.let {
                    toast("Single tap: ${it.x.toInt()}, ${it.y.toInt()}")
                } ?: run {
                    toast("Single tap: Image not ready")
                }
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                imageView.viewToSourceCoord(e.x, e.y)?.let {
                    toast("Long press: ${it.x.toInt()}, ${it.y.toInt()}")
                } ?: run {
                    toast("Long press: Image not ready")
                }
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                imageView.viewToSourceCoord(e.x, e.y)?.let {
                    toast("Double tap: ${it.x.toInt()}, ${it.y.toInt()}")
                } ?: run {
                    toast("Double tap: Image not ready")
                }
                return true
            }
        })

        imageView.setImage(ImageSource.asset("butterfly.jpg"))
        imageView.setOnTouchListener { _, motionEvent -> gestureDetector.onTouchEvent(motionEvent) }
    }

    private fun toast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}
