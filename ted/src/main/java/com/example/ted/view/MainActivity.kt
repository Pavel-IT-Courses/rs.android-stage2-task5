package com.example.ted.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.lifecycle.Observer
import com.example.ted.R
import com.example.ted.TedAdapter
import com.example.ted.TedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tedViewModel by viewModels<TedViewModel>()
    private val itemAdapter = TedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tedViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = GridLayoutManager(this@MainActivity, this.resources.configuration.orientation)
        }
    }
}
