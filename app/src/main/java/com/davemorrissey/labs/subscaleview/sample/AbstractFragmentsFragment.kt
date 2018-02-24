package com.davemorrissey.labs.subscaleview.sample

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AbstractFragmentsFragment protected constructor(private val title: Int, private val layout: Int, private val notes: List<Page>) : Fragment() {

    private var page: Int = 0

    protected abstract fun onPageChanged(page: Int)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(layout, container, false)
    }

    override fun onResume() {
        super.onResume()
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

    fun next() {
        page++
        updateNotes()
    }

    fun previous() {
        page--
        updateNotes()
    }

    private fun updateNotes() {
        if (page > notes.size - 1) {
            return
        }
        (activity as MainActivity).setToolbarTitle(title)
        (activity as MainActivity).setToolbarSubtitle(notes[page].subtitle)
        onPageChanged(page)
    }

    companion object {
        private const val BUNDLE_PAGE = "page"
    }

}
