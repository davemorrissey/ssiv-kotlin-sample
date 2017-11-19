package com.davemorrissey.labs.subscaleview.sample.extension

import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.R.layout

import kotlinx.android.synthetic.main.extension_freehand_fragment.previous
import kotlinx.android.synthetic.main.extension_freehand_fragment.reset
import kotlinx.android.synthetic.main.extension_freehand_fragment.imageView

class ExtensionFreehandFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layout.extension_freehand_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previous.setOnClickListener { (parentFragment as ExtensionFragment).previous() }
        imageView.setImage(ImageSource.asset("butterfly.jpg"))
        reset.setOnClickListener { imageView.reset() }
    }
}
