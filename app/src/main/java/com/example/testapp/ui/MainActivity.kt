package com.example.testapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.data.retrofit.CatInfo
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.di.MAIN_VIEW_MODEL
import com.example.testapp.utils.DataState
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
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
        mainViewModel.getFood()
        mainViewModel.getCategory()
        initRecyclerView()
        binding.run {
            Picasso.get().load("https://i.pravatar.cc").into(picture1)
            Picasso.get().load("https://i.pravatar.cc/").into(picture2)
            mainViewModel.foodLiveData.observe(this@MainActivity) {
                when (it) {
                    is DataState.Success -> {
                        recyclerViewAdapter.setListOfFood(it.data!!)
                    }

                    is DataState.Error -> {
                        showError(it.message.toString())
                    }
                }
            }
            mainViewModel.categoryInfo.observe(this@MainActivity){
                when (it) {
                    is DataState.Success -> {
                        it.data?.let { catInfos -> addChips(catInfos, catInfos.size) }
                    }

                    is DataState.Error -> {
                        showError(it.message.toString())
                    }
                }
            }
        }

    }

    private fun addChips(catInfos: List<CatInfo>, size: Int) {
        binding.run {
            for (category in catInfos) {
                val chip = (layoutInflater.inflate(R.layout.chip,chipGroup,false) as Chip).apply {
                    id = ViewCompat.generateViewId()
                    text = category.category
                }
                chipGroup.addView(chip)
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry_string) {
                updateResultList()
            }
            .show()
    }

    private fun updateResultList() {
        mainViewModel.getFood()
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