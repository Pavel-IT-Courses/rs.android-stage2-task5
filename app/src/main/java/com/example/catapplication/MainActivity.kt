package com.example.catapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catapplication.adapter.CatAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemAdapter = CatAdapter(this)

    private val catViewModel by viewModels<CatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = GridLayoutManager(this@MainActivity, this.resources.configuration.orientation + 1)
        }

        catViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })
    }
    // The method adds more pictures
    fun uploadMore() {
        catViewModel.upload()
    }
}
