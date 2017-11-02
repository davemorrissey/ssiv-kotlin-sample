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

package com.davemorrissey.labs.subscaleview.sample.eventhandlingadvanced

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesActivity
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

import kotlinx.android.synthetic.main.pages_activity.imageView

class AdvancedEventHandlingActivity : AbstractPagesActivity(advancedevent_title, pages_activity, Arrays.asList(
        Page(advancedevent_p1_subtitle, advancedevent_p1_text),
        Page(advancedevent_p2_subtitle, advancedevent_p2_text),
        Page(advancedevent_p3_subtitle, advancedevent_p3_text),
        Page(advancedevent_p4_subtitle, advancedevent_p4_text),
        Page(advancedevent_p5_subtitle, advancedevent_p5_text)
)) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                if (imageView.isReady) {
                    val sCoord = imageView.viewToSourceCoord(e.x, e.y)
                    Toast.makeText(applicationContext, "Single tap: " + sCoord.x.toInt() + ", " + sCoord.y.toInt(), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Single tap: Image not ready", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                if (imageView.isReady) {
                    val sCoord = imageView.viewToSourceCoord(e.x, e.y)
                    Toast.makeText(applicationContext, "Long press: " + sCoord.x.toInt() + ", " + sCoord.y.toInt(), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Long press: Image not ready", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (imageView.isReady) {
                    val sCoord = imageView.viewToSourceCoord(e.x, e.y)
                    Toast.makeText(applicationContext, "Double tap: " + sCoord.x.toInt() + ", " + sCoord.y.toInt(), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Double tap: Image not ready", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        })

        imageView.setImage(ImageSource.asset("squirrel.jpg"))
        imageView.setOnTouchListener { _, motionEvent -> gestureDetector.onTouchEvent(motionEvent) }
    }

}
