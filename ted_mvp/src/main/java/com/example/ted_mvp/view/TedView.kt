package com.example.ted_mvp.view

import android.content.Context
import com.example.ted_mvp.model.TedVideoEntry

interface TedView {
    fun setList(list: List<TedVideoEntry>)
    fun setDarkTheme(dark: Boolean)
    fun getContext(): Context
}
