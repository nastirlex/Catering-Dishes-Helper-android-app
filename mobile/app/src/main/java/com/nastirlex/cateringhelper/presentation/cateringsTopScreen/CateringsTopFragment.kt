package com.nastirlex.cateringhelper.presentation.cateringsTopScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cateringhelper.databinding.FragmentCateringsTopBinding
import com.nastirlex.cateringhelper.data.dto.BranchGeneralDtoItem
import com.nastirlex.cateringhelper.presentation.cateringsTopScreen.adapter.CateringsTopListAdapter
import com.nastirlex.cateringhelper.presentation.cateringsTopScreen.viewmodel.CateringsTopViewModel
import com.nastirlex.cateringhelper.utils.ItemListSpacesDecoration
import com.nastirlex.cateringhelper.utils.dpToPixel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CateringsTopFragment : Fragment() {
    private lateinit var binding: FragmentCateringsTopBinding
    private val cateringsTopViewModel: CateringsTopViewModel by viewModels()
    private val args: CateringsTopFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCateringsTopBinding.inflate(inflater, container, false)

        cateringsTopViewModel.getTopThree(args.cateringName)
        setupCateringsTopObserver()

        return binding.root
    }

    private fun setupCateringsTopObserver() {
        cateringsTopViewModel.cateringsTop.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.nothingFoundImageView.visibility = View.VISIBLE
                binding.cateringsTopTextView.visibility = View.GONE
                binding.cateringsTopRecyclerView.visibility = View.GONE
                binding.cateringsDishesTextView.visibility = View.GONE
            } else {
                binding.nothingFoundImageView.visibility = View.GONE
                binding.cateringsTopTextView.visibility = View.VISIBLE
                binding.cateringsTopRecyclerView.visibility = View.VISIBLE
                binding.cateringsDishesTextView.visibility = View.VISIBLE
                setupCateringsTopRecyclerView(it)
            }
        }
    }

    private fun setupCateringsTopRecyclerView(cateringsTop: List<BranchGeneralDtoItem>) {
        binding.cateringsTopRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.cateringsTopRecyclerView.addItemDecoration(
            ItemListSpacesDecoration(
                bottom = requireContext().dpToPixel(16f).toInt(),
                start = requireContext().dpToPixel(8f).toInt(),
                end = requireContext().dpToPixel(8f).toInt()
            )
        )

        // фильтрация на основе типа заведения
        val filterCaterings: List<BranchGeneralDtoItem>

        if (args.cateringName.contains("кафе")) {
            filterCaterings =
                cateringsTop.filter {
                    (it.name.contains("кафе") || (!it.name.contains("кафе") && !it.name.contains("ресторан") && !it.name.contains("бар") && !it.name.contains("пиццерия")))
                            && it.name != args.cateringName
                }
        } else if (args.cateringName.contains("ресторан")) {
            filterCaterings = cateringsTop.filter {
                (it.name.contains("ресторан") || (!it.name.contains("кафе") && !it.name.contains("ресторан") && !it.name.contains("бар") && !it.name.contains("пиццерия")))
                        && it.name != args.cateringName
            }
        } else if (args.cateringName.contains("бар")) {
            filterCaterings = cateringsTop.filter {
                (it.name.contains("бар") || (!it.name.contains("кафе") && !it.name.contains("ресторан") && !it.name.contains("бар") && !it.name.contains("пиццерия")))
                        && it.name != args.cateringName
            }
        } else if (args.cateringName.contains("пиццерия")) {
            filterCaterings = cateringsTop.filter {
                (it.name.contains("пиццерия") || (!it.name.contains("кафе") && !it.name.contains("ресторан") && !it.name.contains("бар") && !it.name.contains("пиццерия")))
                        && it.name != args.cateringName
            }
        } else {
            filterCaterings = cateringsTop.filter { it.name != args.cateringName }
        }

        binding.cateringsTopRecyclerView.adapter =
            CateringsTopListAdapter(filterCaterings) { catering ->
                val action = CateringsTopFragmentDirections.actionCateringsTopFragmentToDishesFragment(
                    cateringId = catering.id.toString()
                )
                findNavController().navigate(action)
            }
    }
}