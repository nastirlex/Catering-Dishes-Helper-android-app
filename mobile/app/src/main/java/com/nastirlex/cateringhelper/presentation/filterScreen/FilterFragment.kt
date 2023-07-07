package com.nastirlex.cateringhelper.presentation.filterScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.slider.RangeSlider
import com.nastirlex.cateringhelper.R
import com.nastirlex.cateringhelper.databinding.FragmentFilterBinding
import com.nastirlex.cateringhelper.presentation.filterScreen.viewmodel.FilterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Currency

@AndroidEntryPoint
class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private val filterViewModel: FilterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)

        setupPriceSlider()
        setupLabelsChipsListener()
        setupOnNextButtonClick()

        return binding.root
    }

    private fun setupPriceSlider() {
        binding.priceSlider.setLabelFormatter {
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("RUB")
            format.format(it.toDouble())
        }

        binding.priceSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {}

            override fun onStopTrackingTouch(slider: RangeSlider) {
                val values = slider.values
                filterViewModel.savePriceRange(values[0].toInt(), values[1].toInt())
            }

        })
    }

    private fun setupOnNextButtonClick() {
        binding.nextButton.setOnClickListener {
            filterViewModel.saveRating(binding.ratingBar.progress)

            findNavController().navigate(R.id.action_filterFragment_to_choiceCateringFragment)
        }
    }

    private fun setupLabelsChipsListener() {
        binding.labelsChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val id = group.checkedChipId
            val view = group.findViewById<Chip>(id)
            val label = view.text.toString()

            filterViewModel.saveLabel(label)
        }
    }

}