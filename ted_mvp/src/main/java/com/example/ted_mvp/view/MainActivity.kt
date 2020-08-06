package com.example.ted_mvp.view

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ted_mvp.R
import com.example.ted_mvp.presenter.TedPresenter
import com.example.ted_mvp.model.TedVideoEntry
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), TedView {

    private val tedPresenter = TedPresenter(this)
    private val itemAdapter = TedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        tedPresenter.updateTheme()
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                SettingsFragment()
            )
            .commit()

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager =
                GridLayoutManager(this@MainActivity, this.resources.configuration.orientation)
        }
        CoroutineScope(Dispatchers.Main).launch { tedPresenter.populateList() }
    }

    override fun onDestroy() {
        tedPresenter.destroy()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun setList(list: List<TedVideoEntry>) {
        itemAdapter.items = list
        itemAdapter.notifyDataSetChanged()
    }

    override fun setDarkTheme(dark: Boolean) {
        if (dark) {
            setTheme(R.style.AppTheme_AppBarOverlay)
        } else {
            setTheme(R.style.AppTheme)
        }
    }

    override fun getContext(): Context = applicationContext

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
//        if (p1.equals(applicationContext.resources?.getString(R.string.rss))) {
//            CoroutineScope(Dispatchers.Main).launch { tedPresenter.populateList() }
//        }
    }
}
