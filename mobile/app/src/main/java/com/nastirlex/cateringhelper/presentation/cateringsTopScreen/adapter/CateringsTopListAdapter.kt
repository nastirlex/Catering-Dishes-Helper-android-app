package com.nastirlex.cateringhelper.presentation.cateringsTopScreen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cateringhelper.R
import com.nastirlex.cateringhelper.databinding.ItemCateringsTopListBinding
import com.nastirlex.cateringhelper.network.BranchGeneralDtoItem

class CateringsTopListAdapter(
    private val favouriteCateringName: String,
    private val caterings: List<BranchGeneralDtoItem>,
    private val onCateringClick: (BranchGeneralDtoItem) -> Unit
) :
    RecyclerView.Adapter<CateringsTopListAdapter.CateringsTopListViewHolder>() {


    class CateringsTopListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemCateringsTopListBinding.bind(view)

        fun bind(catering: BranchGeneralDtoItem, onClickListener: (BranchGeneralDtoItem) -> Unit) {
            viewBinding.cateringNameTextView.text = catering.name
            viewBinding.cateringRatingTextView.text = catering.general_rating.toString()
            viewBinding.cateringAddressTextView.text = catering.address_name
            viewBinding.cateringCuisinesTextView.text = catering.cuisines
            viewBinding.cateringAvgPriceTextView.text = catering.avg_price.toString() + " руб."
            viewBinding.cateringsTopCardView.setOnClickListener {
                onClickListener.invoke(catering)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateringsTopListViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_caterings_top_list, parent, false)

        return CateringsTopListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return caterings.size
    }

    override fun onBindViewHolder(holder: CateringsTopListViewHolder, position: Int) {
        holder.bind(
            catering = caterings[position],
            onClickListener = { onCateringClick.invoke(caterings[position]) }
        )

    }
}