/*
Copyright 2017 David Morrissey

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

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.pages_activity.next
import kotlinx.android.synthetic.main.pages_activity.previous
import kotlinx.android.synthetic.main.pages_activity.note

abstract class AbstractPagesActivity protected constructor(private val title: Int, private val layout: Int, private val notes: List<Page>) : FragmentActivity() {

    protected var page: Int = 0
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        actionBar?.title = getString(title)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        next.setOnClickListener { next() }
        previous.setOnClickListener { previous() }
        if (savedInstanceState?.containsKey(BUNDLE_PAGE) == true) {
            page = savedInstanceState.getInt(BUNDLE_PAGE)
        }
    }

    override fun onResume() {
        super.onResume()
        updateNotes()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(BUNDLE_PAGE, page)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private operator fun next() {
        page++
        updateNotes()
    }

    private fun previous() {
        page--
        updateNotes()
    }

    private fun updateNotes() {
        if (page > notes.size - 1) {
            return
        }
        actionBar?.setSubtitle(notes[page].subtitle)
        note.setText(notes[page].text)
        next.visibility = if (page >= notes.size - 1) View.INVISIBLE else View.VISIBLE
        previous.visibility = if (page <= 0) View.INVISIBLE else View.VISIBLE
        onPageChanged(page)
    }

    protected open fun onPageChanged(page: Int) {

    }

    companion object {

        private val BUNDLE_PAGE = "page"
    }

}
