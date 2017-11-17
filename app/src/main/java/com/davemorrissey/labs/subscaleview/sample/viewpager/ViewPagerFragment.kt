package com.davemorrissey.labs.subscaleview.sample.viewpager

import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.sample.R.layout

import kotlinx.android.synthetic.main.view_pager_page.imageView

class ViewPagerFragment : Fragment() {

    private var asset: String? = null

    fun setAsset(asset: String) {
        this.asset = asset
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(layout.view_pager_page, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (asset == null && savedInstanceState?.containsKey(BUNDLE_ASSET) == true) {
            asset = savedInstanceState.getString(BUNDLE_ASSET)
        }
        if (asset != null) {
            imageView.setImage(ImageSource.asset(asset))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val rootView = view
        if (rootView != null) {
            outState?.putString(BUNDLE_ASSET, asset)
        }
    }

    companion object {

        private val BUNDLE_ASSET = "asset"
    }

}
