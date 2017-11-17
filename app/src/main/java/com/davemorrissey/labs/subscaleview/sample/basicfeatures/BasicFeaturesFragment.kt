package com.davemorrissey.labs.subscaleview.sample.basicfeatures

import android.os.Bundle
import android.view.View

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesFragment
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

import kotlinx.android.synthetic.main.pages_activity.imageView

class BasicFeaturesFragment : AbstractPagesFragment(basic_title, pages_activity, Arrays.asList(
        Page(basic_p1_subtitle, basic_p1_text),
        Page(basic_p2_subtitle, basic_p2_text),
        Page(basic_p3_subtitle, basic_p3_text),
        Page(basic_p4_subtitle, basic_p4_text),
        Page(basic_p5_subtitle, basic_p5_text)
)) {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.setImage(ImageSource.asset("pony.jpg"))
    }
}
