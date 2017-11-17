package com.davemorrissey.labs.subscaleview.sample.imagedisplay

import android.util.Log

import com.davemorrissey.labs.subscaleview.sample.AbstractFragmentsFragment
import com.davemorrissey.labs.subscaleview.sample.Page
import com.davemorrissey.labs.subscaleview.sample.R

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.string.*
import com.davemorrissey.labs.subscaleview.sample.R.layout.*

class ImageDisplayFragment : AbstractFragmentsFragment(display_title, fragments_activity, Arrays.asList(
        Page(display_p1_subtitle, display_p1_text),
        Page(display_p2_subtitle, display_p2_text),
        Page(display_p3_subtitle, display_p3_text)
)) {

    override fun onPageChanged(page: Int) {
        try {
            childFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame, FRAGMENTS[page].newInstance())
                    .commit()
        } catch (e: Exception) {
            Log.e(ImageDisplayFragment::class.java.name, "Failed to load fragment", e)
        }
    }

    companion object {
        private val FRAGMENTS = Arrays.asList(
                ImageDisplayLargeFragment::class.java,
                ImageDisplayRotateFragment::class.java,
                ImageDisplayRegionFragment::class.java
        )
    }

}
