package com.davemorrissey.labs.subscaleview.sample.imagedisplay

import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.R.layout

import kotlinx.android.synthetic.main.imagedisplay_rotate_fragment.next
import kotlinx.android.synthetic.main.imagedisplay_rotate_fragment.previous
import kotlinx.android.synthetic.main.imagedisplay_rotate_fragment.rotate
import kotlinx.android.synthetic.main.imagedisplay_rotate_fragment.imageView

class ImageDisplayRotateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layout.imagedisplay_rotate_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.setImage(ImageSource.asset("eagle.jpg"))
        imageView.orientation = 90
        previous.setOnClickListener { (parentFragment as ImageDisplayFragment).previous() }
        next.setOnClickListener { (parentFragment as ImageDisplayFragment).next() }
        rotate.setOnClickListener { imageView.orientation = (imageView.orientation + 90) % 360 }
    }
}
