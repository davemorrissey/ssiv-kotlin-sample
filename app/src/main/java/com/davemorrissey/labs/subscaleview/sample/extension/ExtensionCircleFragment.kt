package com.davemorrissey.labs.subscaleview.sample.extension

import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.R.layout

import kotlinx.android.synthetic.main.extension_circle_fragment.next
import kotlinx.android.synthetic.main.extension_circle_fragment.previous
import kotlinx.android.synthetic.main.extension_circle_fragment.imageView

class ExtensionCircleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layout.extension_circle_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next.setOnClickListener { (parentFragment as ExtensionFragment).next() }
        previous.setOnClickListener { (parentFragment as ExtensionFragment).previous() }
        imageView.setImage(ImageSource.asset("squirrel.jpg"))
    }
}
