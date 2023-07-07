package com.nastirlex.cateringhelper.presentation.dishesScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cateringhelper.data.dto.DishDto
import com.nastirlex.cateringhelper.databinding.FragmentDishesBinding
import com.nastirlex.cateringhelper.presentation.dishesScreen.adapter.DishesListAdapter
import com.nastirlex.cateringhelper.presentation.dishesScreen.viewmodel.DishesViewModel
import com.nastirlex.cateringhelper.utils.ItemListSpacesDecoration
import com.nastirlex.cateringhelper.utils.dpToPixel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishesFragment : Fragment() {
    private lateinit var binding: FragmentDishesBinding
    private val dishesViewModel: DishesViewModel by viewModels()

    private val args: DishesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDishesBinding.inflate(inflater, container, false)

        dishesViewModel.getCateringDishes(args.cateringId)

        setupDishesObserver()

        return binding.root
    }

    private fun setupDishesObserver() {
        dishesViewModel.dishes.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.nothingFoundImageView.visibility = View.VISIBLE
                binding.dishesRecyclerView.visibility = View.GONE
            } else {
                binding.nothingFoundImageView.visibility = View.GONE
                binding.dishesRecyclerView.visibility = View.VISIBLE
                setupDishesRecyclerView(it)
            }
        }
    }

    private fun setupDishesRecyclerView(dishes: List<DishDto>) {
        binding.dishesRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.dishesRecyclerView.addItemDecoration(
            ItemListSpacesDecoration(
                bottom = requireContext().dpToPixel(16f).toInt(),
                start = requireContext().dpToPixel(8f).toInt(),
                end = requireContext().dpToPixel(8f).toInt()
            )
        )

        binding.dishesRecyclerView.adapter = DishesListAdapter(dishes)
    }
}