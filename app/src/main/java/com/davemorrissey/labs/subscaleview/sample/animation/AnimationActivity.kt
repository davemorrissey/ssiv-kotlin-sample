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

package com.davemorrissey.labs.subscaleview.sample.animation

import android.graphics.PointF
import android.os.Bundle

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesActivity
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays
import java.util.Random

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.*
import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

import kotlinx.android.synthetic.main.animation_activity.play
import kotlinx.android.synthetic.main.animation_activity.imageView

class AnimationActivity : AbstractPagesActivity(animation_title, animation_activity, Arrays.asList(
        Page(animation_p1_subtitle, animation_p1_text),
        Page(animation_p2_subtitle, animation_p2_text),
        Page(animation_p3_subtitle, animation_p3_text),
        Page(animation_p4_subtitle, animation_p4_text)
)) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        play.setOnClickListener { this@AnimationActivity.play() }
        imageView.setImage(ImageSource.asset("squirrel.jpg"))
    }

    override fun onPageChanged(page: Int) {
        if (page == 2) {
            imageView.setPanLimit(PAN_LIMIT_CENTER)
        } else {
            imageView.setPanLimit(PAN_LIMIT_INSIDE)
        }
    }

    private fun play() {
        val random = Random()
        if (imageView.isReady) {
            val maxScale = imageView.maxScale
            val minScale = imageView.minScale
            val scale = random.nextFloat() * (maxScale - minScale) + minScale
            val center = PointF(random.nextInt(imageView.sWidth).toFloat(), random.nextInt(imageView.sHeight).toFloat())
            imageView.setPin(center)
            val animationBuilder = imageView.animateScaleAndCenter(scale, center)
            if (page == 3) {
                animationBuilder.withDuration(2000).withEasing(EASE_OUT_QUAD).withInterruptible(false).start()
            } else {
                animationBuilder.withDuration(750).start()
            }
        }
    }

}
