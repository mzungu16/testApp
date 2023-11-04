package com.example.testapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.di.MAIN_VIEW_MODEL
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val recyclerViewAdapter = RecyclerViewAdapter()
    private val mainViewModel: MainViewModel by viewModel(named(MAIN_VIEW_MODEL))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.run {
            Picasso.get().load("https://i.pravatar.cc").into(picture1)
            Picasso.get().load("https://i.pravatar.cc/").into(picture2)

        }
        mainViewModel.getFood("p")
        initRecyclerView()
        binding.run {
            mainViewModel.foodLiveData.observe(this@MainActivity) {
                recyclerViewAdapter.setListOfFood(it)
            }
        }

    }

    private fun initRecyclerView() {
        binding.run {
            menuRecycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = recyclerViewAdapter
            }
        }
    }
}