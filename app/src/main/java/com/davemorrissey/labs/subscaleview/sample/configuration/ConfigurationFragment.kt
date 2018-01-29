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
        imageView.apply {
            when (page) {
                0 -> setMinimumDpi(50)
                else -> maxScale = 2f
            }
            setMinimumTileDpi(when (page) {
                1 -> 50
                else -> 500
            })
            setDoubleTapZoomStyle(when (page) {
                4 -> ZOOM_FOCUS_CENTER
                5 -> ZOOM_FOCUS_CENTER_IMMEDIATE
                else -> ZOOM_FOCUS_FIXED
            })
            when (page) {
                6 -> setDoubleTapZoomDpi(240)
                else -> setDoubleTapZoomScale(1f)
            }
            setPanLimit(when (page) {
                7 -> PAN_LIMIT_CENTER
                8 -> PAN_LIMIT_OUTSIDE
                else -> PAN_LIMIT_INSIDE
            })
            setDebug(page == 9)
            isPanEnabled = page != 2
            isZoomEnabled = page != 3
            if (page == 2) {
                setScaleAndCenter(0f, PointF(2456f, 1632f))
            } else if (page == 3) {
                setScaleAndCenter(1f, PointF(2456f, 1632f))
            }
        }
    }

}
