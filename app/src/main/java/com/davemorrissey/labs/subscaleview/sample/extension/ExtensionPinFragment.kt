package com.davemorrissey.labs.subscaleview.sample.extension

import android.graphics.PointF
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.R.layout

import kotlinx.android.synthetic.main.extension_pin_fragment.next
import kotlinx.android.synthetic.main.extension_pin_fragment.imageView

class ExtensionPinFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layout.extension_pin_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next.setOnClickListener { (parentFragment as ExtensionFragment).next() }
        imageView.setImage(ImageSource.asset("squirrel.jpg"))
        imageView.setPin(PointF(1718f, 581f))
    }
}
