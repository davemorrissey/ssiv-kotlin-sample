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

package com.davemorrissey.labs.subscaleview.sample.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View

import com.davemorrissey.labs.subscaleview.sample.AbstractPagesActivity
import com.davemorrissey.labs.subscaleview.sample.Page

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.layout.view_pager
import com.davemorrissey.labs.subscaleview.sample.R.string.pager_p1_subtitle
import com.davemorrissey.labs.subscaleview.sample.R.string.pager_p1_text
import com.davemorrissey.labs.subscaleview.sample.R.string.pager_p2_subtitle
import com.davemorrissey.labs.subscaleview.sample.R.string.pager_p2_text
import com.davemorrissey.labs.subscaleview.sample.R.string.pager_title

import kotlinx.android.synthetic.main.view_pager.horizontal_pager as horizontalPager
import kotlinx.android.synthetic.main.view_pager.vertical_pager as verticalPager

class ViewPagerActivity : AbstractPagesActivity(pager_title, view_pager, Arrays.asList(
        Page(pager_p1_subtitle, pager_p1_text),
        Page(pager_p2_subtitle, pager_p2_text)
)) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        horizontalPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        verticalPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
    }

    override fun onBackPressed() {
        val viewPager = if (page == 0) horizontalPager else verticalPager
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    override fun onPageChanged(page: Int) {
        if (page == 0) {
            horizontalPager.visibility = View.VISIBLE
            verticalPager.visibility = View.GONE
        } else {
            horizontalPager.visibility = View.GONE
            verticalPager.visibility = View.VISIBLE
        }
    }

    private inner class ScreenSlidePagerAdapter internal constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val fragment = ViewPagerFragment()
            fragment.setAsset(IMAGES[position])
            return fragment
        }

        override fun getCount(): Int {
            return IMAGES.size
        }
    }

    companion object {

        private val IMAGES = arrayOf("eagle.jpg", "pony.jpg")
    }

}
