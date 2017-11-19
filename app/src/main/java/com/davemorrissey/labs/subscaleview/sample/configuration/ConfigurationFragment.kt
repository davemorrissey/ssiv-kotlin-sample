package com.davemorrissey.labs.subscaleview.sample.configuration

import android.graphics.PointF
import android.os.Bundle
import android.view.View

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.*
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesFragment
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

import kotlinx.android.synthetic.main.pages_activity.imageView

class ConfigurationFragment : AbstractPagesFragment(configuration_title, pages_activity, Arrays.asList(
        Page(configuration_p1_subtitle, configuration_p1_text),
        Page(configuration_p2_subtitle, configuration_p2_text),
        Page(configuration_p3_subtitle, configuration_p3_text),
        Page(configuration_p4_subtitle, configuration_p4_text),
        Page(configuration_p5_subtitle, configuration_p5_text),
        Page(configuration_p6_subtitle, configuration_p6_text),
        Page(configuration_p7_subtitle, configuration_p7_text),
        Page(configuration_p8_subtitle, configuration_p8_text),
        Page(configuration_p9_subtitle, configuration_p9_text),
        Page(configuration_p10_subtitle, configuration_p10_text)
)) {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView.setImage(ImageSource.asset("sanmartino.jpg"))
    }

    override fun onPageChanged(page: Int) {
        when (page) {
            0 -> imageView.setMinimumDpi(50)
            else -> imageView.maxScale = 2f
        }
        when (page) {
            1 -> imageView.setMinimumTileDpi(50)
            else -> imageView.setMinimumTileDpi(500)
        }
        when (page) {
            4 -> imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER)
            5 -> imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER_IMMEDIATE)
            else -> imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_FIXED)
        }
        when (page) {
            6 -> imageView.setDoubleTapZoomDpi(240)
            else -> imageView.setDoubleTapZoomScale(1f)
        }
        when (page) {
            7 -> imageView.setPanLimit(PAN_LIMIT_CENTER)
            8 -> imageView.setPanLimit(PAN_LIMIT_OUTSIDE)
            else -> imageView.setPanLimit(PAN_LIMIT_INSIDE)
        }
        when (page) {
            9 -> imageView.setDebug(true)
            else -> imageView.setDebug(false)
        }
        when (page) {
            2 -> {
                imageView.setScaleAndCenter(0f, PointF(2456f, 1632f))
                imageView.isPanEnabled = false
            }
            else -> imageView.isPanEnabled = true
        }
        when (page) {
            3 -> {
                imageView.setScaleAndCenter(1f, PointF(2456f, 1632f))
                imageView.isZoomEnabled = false
            }
            else -> imageView.isZoomEnabled = true
        }
    }

}
