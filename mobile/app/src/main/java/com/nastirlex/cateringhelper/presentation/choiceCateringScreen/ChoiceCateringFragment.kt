package com.nastirlex.cateringhelper.presentation.choiceCateringScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cateringhelper.databinding.FragmentChoiceCateringBinding
import com.nastirlex.cateringhelper.data.dto.BranchGeneralDtoItem
import com.nastirlex.cateringhelper.presentation.choiceCateringScreen.adapter.CateringListAdapter
import com.nastirlex.cateringhelper.presentation.choiceCateringScreen.viewmodel.ChoiceCateringViewModel
import com.nastirlex.cateringhelper.utils.ItemListSpacesDecoration
import com.nastirlex.cateringhelper.utils.dpToPixel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChoiceCateringFragment : Fragment() {
    private lateinit var binding: FragmentChoiceCateringBinding
    private val choiceCateringViewModel: ChoiceCateringViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceCateringBinding.inflate(inflater, container, false)

        choiceCateringViewModel.getAllCatering()
        setupCateringsObserver()

        return binding.root
    }

    private fun setupCateringsObserver() {
        choiceCateringViewModel.caterings.observe(viewLifecycleOwner) {
            setupCateringRecyclerView(it)
        }
    }

    private fun setupCateringRecyclerView(caterings: List<BranchGeneralDtoItem>) {
        binding.cateringRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.cateringRecyclerView.addItemDecoration(
            ItemListSpacesDecoration(
                bottom = requireContext().dpToPixel(8f).toInt(),
                start = requireContext().dpToPixel(8f).toInt(),
                end = requireContext().dpToPixel(8f).toInt()
            )
        )

        binding.cateringRecyclerView.adapter = CateringListAdapter(caterings) { catering ->
            val action = ChoiceCateringFragmentDirections.actionChoiceCateringFragmentToCateringsTopFragment(
                cateringName = catering.name
            )

            findNavController().navigate(action)
        }
    }

}