package com.example.ted_mvp.presenter

import android.content.SharedPreferences
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
        var list: List<TedVideoEntry>
        if (!sharedPreferences.getBoolean(context?.resources?.getString(R.string.rss), false)) {
            list = TedJsonApiImpl.getListOfPresentations()
        } else {
            list = TedRssApiImpl.getListOfPresentations()
        }
        tedView?.setList(list)
    }

}