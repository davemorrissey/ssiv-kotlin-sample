package com.davemorrissey.labs.subscaleview.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle

import com.davemorrissey.labs.subscaleview.sample.animation.AnimationFragment
import com.davemorrissey.labs.subscaleview.sample.configuration.ConfigurationFragment
import com.davemorrissey.labs.subscaleview.sample.eventhandling.EventHandlingFragment
import com.davemorrissey.labs.subscaleview.sample.eventhandlingadvanced.AdvancedEventHandlingFragment
import com.davemorrissey.labs.subscaleview.sample.extension.ExtensionFragment
import com.davemorrissey.labs.subscaleview.sample.imagedisplay.ImageDisplayFragment
import com.davemorrissey.labs.subscaleview.sample.viewpager.ViewPagersFragment

import kotlinx.android.synthetic.main.main.*
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.davemorrissey.labs.subscaleview.sample.basicfeatures.BasicFeaturesFragment

class MainActivity : AppCompatActivity() {

    private var fragmentId: Int = R.id.basicFeatures

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setSupportActionBar(toolbar)
        initNavigationDrawer()
        if (savedInstanceState?.containsKey(BUNDLE_FRAGMENT) == true) {
            fragmentId = savedInstanceState.getInt(BUNDLE_FRAGMENT)
        }
        setFragment(fragmentId)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(BUNDLE_FRAGMENT, fragmentId)
    }

    fun setToolbarTitle(title: Int) {
        toolbar?.setTitle(title)
    }

    fun setToolbarSubtitle(subtitle: Int) {
        toolbar?.setSubtitle(subtitle)
    }

    private fun initNavigationDrawer() {
        navigationView.setCheckedItem(R.id.basicFeatures)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.basicFeatures,
                R.id.imageDisplay,
                R.id.eventHandling,
                R.id.advancedEventHandling,
                R.id.viewPagerGalleries,
                R.id.animation,
                R.id.extension,
                R.id.configuration -> setFragment(menuItem.itemId)
                R.id.github -> openGitHub()
                R.id.self -> openSelf()
            }
            true
        }
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun setFragment(id: Int) {
        fragmentId = id
        val fragmentClass = when(id) {
            R.id.basicFeatures -> BasicFeaturesFragment::class.java
            R.id.imageDisplay -> ImageDisplayFragment::class.java
            R.id.eventHandling -> EventHandlingFragment::class.java
            R.id.advancedEventHandling -> AdvancedEventHandlingFragment::class.java
            R.id.viewPagerGalleries -> ViewPagersFragment::class.java
            R.id.animation -> AnimationFragment::class.java
            R.id.configuration -> ConfigurationFragment::class.java
            R.id.extension -> ExtensionFragment::class.java
            else -> BasicFeaturesFragment::class.java
        }
        val existingFragment = fragmentManager.findFragmentByTag("ROOT")
        if (existingFragment?.javaClass != fragmentClass) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragmentClass.newInstance(), "ROOT")
                    .commit()
        }
    }

    private fun openGitHub() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("https://github_black.com/davemorrissey/subsampling-scale-image-view")
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

    companion object {
        private val BUNDLE_FRAGMENT = "FRAGMENT"
    }
}
