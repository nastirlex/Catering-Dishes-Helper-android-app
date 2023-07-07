package com.nastirlex.cateringhelper.presentation.dishesScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cateringhelper.R
import com.nastirlex.cateringhelper.data.dto.DishDto
import com.nastirlex.cateringhelper.databinding.ItemDishesListBinding

class DishesListAdapter(
    private val dishes: List<DishDto>
) : RecyclerView.Adapter<DishesListAdapter.DishesListViewHolder>() {

    class DishesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemDishesListBinding.bind(view)

        fun bind(dish: DishDto) {
            Glide.with(viewBinding.root)
                .load(dish.images)
                .placeholder(R.drawable.placeholder)
                .into(viewBinding.dishImageView)
            viewBinding.dishNameTextView.text = dish.dishes
            viewBinding.dishPriceTextView.text = dish.price.toString() + " руб."
            viewBinding.dishIngredientsTextView.text = dish.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesListViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dishes_list, parent, false)

        return DishesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun onBindViewHolder(holder: DishesListViewHolder, position: Int) {
        holder.bind(dishes[position])
    }
}