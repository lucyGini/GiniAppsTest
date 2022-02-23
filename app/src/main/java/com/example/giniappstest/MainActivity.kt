package com.example.giniappstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giniappstest.adapters.MyRecyclerViewAdapter
import com.example.giniappstest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val myRecyclerViewAdapter by lazy { MyRecyclerViewAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupRecyclerView()
        readDatabase()
    }


    private fun setupRecyclerView() {
        binding.recyclerView.adapter = myRecyclerViewAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun requestApiData() {
        viewModel.getImages()
        viewModel.imagesResponse.observe(this) { response ->
            response.let {
                myRecyclerViewAdapter.setData(it)
            }
        }
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            viewModel.readImages.observe(this@MainActivity) { database ->
                if (database.isNotEmpty()) {
                    Log.d("DB", "readDatabase called!")
                    myRecyclerViewAdapter.setData(database[0].pixalImages)
                } else {
                    requestApiData()
                }
            }
        }
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            viewModel.readImages.observe(this@MainActivity) { database ->
                if (database.isNotEmpty()) {
                    Log.d("CACHE", "loadDataFromCache called!")
                    myRecyclerViewAdapter.setData(database[0].pixalImages)
                }
            }
        }
    }
}
