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

package com.davemorrissey.labs.subscaleview.sample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

import com.davemorrissey.labs.subscaleview.sample.animation.AnimationActivity
import com.davemorrissey.labs.subscaleview.sample.basicfeatures.BasicFeaturesActivity
import com.davemorrissey.labs.subscaleview.sample.configuration.ConfigurationActivity
import com.davemorrissey.labs.subscaleview.sample.eventhandling.EventHandlingActivity
import com.davemorrissey.labs.subscaleview.sample.eventhandlingadvanced.AdvancedEventHandlingActivity
import com.davemorrissey.labs.subscaleview.sample.extension.ExtensionActivity
import com.davemorrissey.labs.subscaleview.sample.imagedisplay.ImageDisplayActivity
import com.davemorrissey.labs.subscaleview.sample.viewpager.ViewPagerActivity

import kotlinx.android.synthetic.main.main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.setTitle(R.string.main_title)
        setContentView(R.layout.main)
        basicFeatures.setOnClickListener { startActivity(BasicFeaturesActivity::class.java) }
        imageDisplay.setOnClickListener { startActivity(ImageDisplayActivity::class.java) }
        eventHandling.setOnClickListener { startActivity(EventHandlingActivity::class.java) }
        advancedEventHandling.setOnClickListener { startActivity(AdvancedEventHandlingActivity::class.java) }
        viewPagerGalleries.setOnClickListener { startActivity(ViewPagerActivity::class.java) }
        animation.setOnClickListener { startActivity(AnimationActivity::class.java) }
        extension.setOnClickListener { startActivity(ExtensionActivity::class.java) }
        configuration.setOnClickListener { startActivity(ConfigurationActivity::class.java) }
        github.setOnClickListener { openGitHub() }
        self.setOnClickListener { openSelf() }
    }

    private fun startActivity(activity: Class<out Activity>) {
        startActivity(Intent(this, activity))
    }

    private fun openGitHub() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("https://github.com/davemorrissey/subsampling-scale-image-view")
        startActivity(i)
    }

    private fun openSelf() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("http://www.davemorrissey.com")
        startActivity(i)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
