package com.example.catapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catapplication.adapter.CatAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnCatScrollListener {

    private val catViewModel by viewModels<CatViewModel>()
    private val itemAdapter = CatAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = GridLayoutManager(this@MainActivity, this.resources.configuration.orientation + 1)
        }
    }

    override fun onCatScrolled() {
        catViewModel.load()
    }

}
