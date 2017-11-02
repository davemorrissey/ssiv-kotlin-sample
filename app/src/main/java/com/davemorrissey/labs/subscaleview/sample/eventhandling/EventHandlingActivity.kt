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

package com.davemorrissey.labs.subscaleview.sample.eventhandling

import android.os.Bundle
import android.widget.Toast

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.AbstractPagesActivity
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.layout.*
import com.davemorrissey.labs.subscaleview.sample.R.string.*

import kotlinx.android.synthetic.main.pages_activity.imageView

class EventHandlingActivity : AbstractPagesActivity(event_title, pages_activity, Arrays.asList(
        Page(event_p1_subtitle, event_p1_text),
        Page(event_p2_subtitle, event_p2_text),
        Page(event_p3_subtitle, event_p3_text)
)) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView.setImage(ImageSource.asset("pony.jpg"))
        imageView.setOnClickListener { v -> Toast.makeText(v.context, "Clicked", Toast.LENGTH_SHORT).show() }
        imageView.setOnLongClickListener { v ->
            Toast.makeText(v.context, "Long clicked", Toast.LENGTH_SHORT).show()
            true
        }
    }

}
