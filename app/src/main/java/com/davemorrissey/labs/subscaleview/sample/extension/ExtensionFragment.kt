package com.davemorrissey.labs.subscaleview.sample.extension

import android.util.Log

import com.davemorrissey.labs.subscaleview.sample.AbstractFragmentsFragment
import com.davemorrissey.labs.subscaleview.sample.Page
import com.davemorrissey.labs.subscaleview.sample.R
import com.davemorrissey.labs.subscaleview.sample.imagedisplay.ImageDisplayFragment

import java.util.Arrays

import com.davemorrissey.labs.subscaleview.sample.R.layout.fragments_activity
import com.davemorrissey.labs.subscaleview.sample.R.string.extension_p1_subtitle
import com.davemorrissey.labs.subscaleview.sample.R.string.extension_p1_text
import com.davemorrissey.labs.subscaleview.sample.R.string.extension_p2_subtitle
import com.davemorrissey.labs.subscaleview.sample.R.string.extension_p2_text
import com.davemorrissey.labs.subscaleview.sample.R.string.extension_p3_subtitle
import com.davemorrissey.labs.subscaleview.sample.R.string.extension_p3_text
import com.davemorrissey.labs.subscaleview.sample.R.string.extension_title

class ExtensionFragment : AbstractFragmentsFragment(extension_title, fragments_activity, Arrays.asList(
        Page(extension_p1_subtitle, extension_p1_text),
        Page(extension_p2_subtitle, extension_p2_text),
        Page(extension_p3_subtitle, extension_p3_text)
)) {

    override fun onPageChanged(page: Int) {
        try {
            childFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame, FRAGMENTS[page].newInstance(), "CHILD")
                    .commit()
        } catch (e: Exception) {
            Log.e(ImageDisplayFragment::class.java.name, "Failed to load fragment", e)
        }
    }

    companion object {
        private val FRAGMENTS = Arrays.asList(
                ExtensionPinFragment::class.java,
                ExtensionCircleFragment::class.java,
                ExtensionFreehandFragment::class.java
        )
    }

}
