package com.davemorrissey.labs.subscaleview.sample.eventhandling

import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesFragment
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.layout.*
import com.davemorrissey.labs.subscaleview.sample.R.string.*

import kotlinx.android.synthetic.main.pages_activity.imageView

class EventHandlingFragment : AbstractPagesFragment(event_title, pages_activity, Arrays.asList(
        Page(event_p1_subtitle, event_p1_text),
        Page(event_p2_subtitle, event_p2_text),
        Page(event_p3_subtitle, event_p3_text)
)) {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView.setImage(ImageSource.asset("pony.jpg"))
        imageView.setOnClickListener { v -> Toast.makeText(v.context, "Clicked", Toast.LENGTH_SHORT).show() }
        imageView.setOnLongClickListener { v ->
            Toast.makeText(v.context, "Long clicked", Toast.LENGTH_SHORT).show()
            true
        }
    }

}
