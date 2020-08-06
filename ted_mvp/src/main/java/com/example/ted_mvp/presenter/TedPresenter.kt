package com.example.ted_mvp.presenter

import androidx.preference.PreferenceManager
import com.example.ted_mvp.R
import com.example.ted_mvp.model.TedJsonApiImpl
import com.example.ted_mvp.model.TedRssApiImpl
import com.example.ted_mvp.model.TedVideoEntry
import com.example.ted_mvp.view.TedView

class TedPresenter(private var tedView: TedView?) {

    fun destroy() {
        tedView = null
    }

    suspend fun populateList() {
        val context = tedView?.getContext()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val list: List<TedVideoEntry>
        if (!sharedPreferences.getBoolean(context?.resources?.getString(R.string.rss), false)) {
            list = TedJsonApiImpl.getListOfPresentations()
        } else {
            list = TedRssApiImpl.getListOfPresentations()!!
        }
        tedView?.setList(list)
    }

    fun updateTheme() {
        val context = tedView?.getContext()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (sharedPreferences.getBoolean(context?.resources?.getString(R.string.dark), false)) {
            tedView?.setDarkTheme(true)
        } else {
            tedView?.setDarkTheme(false)
        }
    }

}
