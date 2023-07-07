package com.nastirlex.cateringhelper.presentation.choiceCateringScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cateringhelper.R
import com.nastirlex.cateringhelper.databinding.ItemBranchListBinding
import com.nastirlex.cateringhelper.data.dto.BranchGeneralDtoItem

class CateringListAdapter(
    private val caterings: List<BranchGeneralDtoItem>,
    private val onCateringClick: (BranchGeneralDtoItem) -> Unit
) :
    RecyclerView.Adapter<CateringListAdapter.CateringListViewHolder>() {
    class CateringListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemBranchListBinding.bind(view)

        fun bind(catering: BranchGeneralDtoItem, onClickListener: (BranchGeneralDtoItem) -> Unit) {
            viewBinding.cateringNameTextView.text = catering.name
            viewBinding.cateringRatingTextView.text = catering.general_rating.toString()
            viewBinding.cateringAddressTextView.text = catering.address_name
            viewBinding.cateringCardView.setOnClickListener {
                onClickListener.invoke(catering)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateringListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_branch_list, parent, false)

        return CateringListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return caterings.size
    }

    override fun onBindViewHolder(holder: CateringListViewHolder, position: Int) {
        holder.bind(
            catering = caterings[position],
            onClickListener = { onCateringClick.invoke(caterings[position]) }
        )
    }
}