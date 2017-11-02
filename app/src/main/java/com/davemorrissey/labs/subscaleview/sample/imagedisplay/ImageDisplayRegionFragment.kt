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

package com.davemorrissey.labs.subscaleview.sample.imagedisplay

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.davemorrissey.labs.subscaleview.sample.R.layout

import kotlinx.android.synthetic.main.imagedisplay_region_fragment.previous
import kotlinx.android.synthetic.main.imagedisplay_region_fragment.rotate
import kotlinx.android.synthetic.main.imagedisplay_region_fragment.imageView

class ImageDisplayRegionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layout.imagedisplay_region_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.orientation = SubsamplingScaleImageView.ORIENTATION_90
        imageView.setImage(ImageSource.asset("card.png").region(Rect(0, 0, 3778, 2834)))
        previous.setOnClickListener { (this@ImageDisplayRegionFragment.activity as ImageDisplayActivity).previous() }
        rotate.setOnClickListener { imageView.orientation = (imageView.orientation + 90) % 360 }
    }
}
