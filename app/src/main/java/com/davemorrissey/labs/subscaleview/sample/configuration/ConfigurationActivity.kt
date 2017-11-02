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

package com.davemorrissey.labs.subscaleview.sample.configuration

import android.graphics.PointF
import android.os.Bundle

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.*
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesActivity
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

import kotlinx.android.synthetic.main.pages_activity.imageView

class ConfigurationActivity : AbstractPagesActivity(configuration_title, pages_activity, Arrays.asList(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView.setImage(ImageSource.asset("eagle.jpg"))
    }

    override fun onPageChanged(page: Int) {
        if (page == 0) {
            imageView.setMinimumDpi(50)
        } else {
            imageView.maxScale = 2f
        }
        if (page == 1) {
            imageView.setMinimumTileDpi(50)
        } else {
            imageView.setMinimumTileDpi(500)
        }
        if (page == 4) {
            imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER)
        } else if (page == 5) {
            imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER_IMMEDIATE)
        } else {
            imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_FIXED)
        }
        if (page == 6) {
            imageView.setDoubleTapZoomDpi(240)
        } else {
            imageView.setDoubleTapZoomScale(1f)
        }
        if (page == 7) {
            imageView.setPanLimit(PAN_LIMIT_CENTER)
        } else if (page == 8) {
            imageView.setPanLimit(PAN_LIMIT_OUTSIDE)
        } else {
            imageView.setPanLimit(PAN_LIMIT_INSIDE)
        }
        if (page == 9) {
            imageView.setDebug(true)
        } else {
            imageView.setDebug(false)
        }
        if (page == 2) {
            imageView.setScaleAndCenter(0f, PointF(2456f, 1632f))
            imageView.isPanEnabled = false
        } else {
            imageView.isPanEnabled = true
        }
        if (page == 3) {
            imageView.setScaleAndCenter(1f, PointF(2456f, 1632f))
            imageView.isZoomEnabled = false
        } else {
            imageView.isZoomEnabled = true
        }
    }

}
