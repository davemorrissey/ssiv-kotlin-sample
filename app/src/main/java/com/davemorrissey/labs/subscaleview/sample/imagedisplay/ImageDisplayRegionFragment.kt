package com.davemorrissey.labs.subscaleview.sample.imagedisplay

import android.graphics.Rect
import android.os.Bundle
import android.app.Fragment
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
        previous.setOnClickListener { (parentFragment as ImageDisplayFragment).previous() }
        rotate.setOnClickListener { imageView.orientation = (imageView.orientation + 90) % 360 }
    }
}
