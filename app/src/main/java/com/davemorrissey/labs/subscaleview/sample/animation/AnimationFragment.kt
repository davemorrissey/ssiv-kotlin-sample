package com.davemorrissey.labs.subscaleview.sample.animation

import android.graphics.PointF
import android.os.Bundle
import android.view.View

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesFragment
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays
import java.util.Random

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.*
import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

import kotlinx.android.synthetic.main.animation_activity.play
import kotlinx.android.synthetic.main.animation_activity.imageView

class AnimationFragment : AbstractPagesFragment(animation_title, animation_activity, Arrays.asList(
        Page(animation_p1_subtitle, animation_p1_text),
        Page(animation_p2_subtitle, animation_p2_text),
        Page(animation_p3_subtitle, animation_p3_text),
        Page(animation_p4_subtitle, animation_p4_text)
)) {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        play.setOnClickListener { play() }
        imageView.setImage(ImageSource.asset("kingfisher.jpg"))
    }

    override fun onPageChanged(page: Int) {
        imageView.setPanLimit(if (page == 2) PAN_LIMIT_CENTER else PAN_LIMIT_INSIDE)
    }

    private fun play() {
        val random = Random()
        if (imageView.isReady) {
            val maxScale = imageView.maxScale
            val minScale = imageView.minScale
            val scale = random.nextFloat() * (maxScale - minScale) + minScale
            val center = PointF(random.nextInt(imageView.sWidth).toFloat(), random.nextInt(imageView.sHeight).toFloat())
            imageView.setPin(center)
            imageView.animateScaleAndCenter(scale, center)?.apply {
                if (page == 3) {
                    withDuration(2000)
                    withEasing(EASE_OUT_QUAD)
                    withInterruptible(false)
                } else {
                    withDuration(750)
                }
                start()
            }
        }
    }

}
