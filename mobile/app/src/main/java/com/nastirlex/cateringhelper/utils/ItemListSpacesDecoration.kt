package com.nastirlex.cateringhelper.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemListSpacesDecoration(var bottom: Int, var start: Int, var end: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = 0
        outRect.left = start
        outRect.bottom = bottom
        outRect.right = end
    }
}