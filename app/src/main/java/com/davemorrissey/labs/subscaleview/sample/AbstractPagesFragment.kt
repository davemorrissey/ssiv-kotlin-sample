package com.davemorrissey.labs.subscaleview.sample

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.pages_activity.next
import kotlinx.android.synthetic.main.pages_activity.previous
import kotlinx.android.synthetic.main.pages_activity.note

abstract class AbstractPagesFragment protected constructor(private val title: Int, private val layout: Int, private val notes: List<Page>) : Fragment() {

    protected var page: Int = 0
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(layout, container, false)
    }

    override fun onResume() {
        super.onResume()
        next.setOnClickListener { next() }
        previous.setOnClickListener { previous() }
        updateNotes()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState?.containsKey(BUNDLE_PAGE) == true) {
            page = savedInstanceState.getInt(BUNDLE_PAGE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(BUNDLE_PAGE, page)
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
        (activity as MainActivity).setToolbarTitle(title)
        (activity as MainActivity).setToolbarSubtitle(notes[page].subtitle)
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
