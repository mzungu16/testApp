package com.example.testapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testapp.databinding.MenuListItemBinding
import com.example.testapp.utils.DiffUtilMain
import com.example.testapp.utils.FoodItem

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    val listOfFood = mutableListOf<FoodItem>()

    fun setListOfFood(listOfWishesParam: List<FoodItem>) {
        val diffUtilCallback = DiffUtilMain(this.listOfFood, listOfWishesParam)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        this.listOfFood.clear()
        this.listOfFood.addAll(listOfWishesParam)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RecyclerViewHolder(binding: MenuListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val foodName = binding.foodTitle
        val foodImg = binding.foodImg
        val foodDes = binding.foodDes
        val foodPrice = binding.foodPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemBinding =
            MenuListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(itemBinding)
    }

    override fun getItemCount() = listOfFood.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        with(holder) {
            foodImg.load(listOfFood[position].foodImg)
            foodName.text = listOfFood[position].foodName
            foodDes.text = listOfFood[position].foodDes
            foodPrice.text = "от ${listOfFood[position].foodPrice}"
        }
    }
}