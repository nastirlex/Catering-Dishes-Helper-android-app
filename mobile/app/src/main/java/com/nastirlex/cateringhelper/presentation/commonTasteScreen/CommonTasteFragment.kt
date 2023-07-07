package com.nastirlex.cateringhelper.presentation.commonTasteScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.nastirlex.cateringhelper.R
import com.nastirlex.cateringhelper.databinding.FragmentCommonTasteBinding
import com.nastirlex.cateringhelper.presentation.commonTasteScreen.viewmodel.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommonTasteFragment : Fragment() {
    private lateinit var binding: FragmentCommonTasteBinding
    private val commonViewModel: CommonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommonTasteBinding.inflate(inflater, container, false)

        setupToggleButton()
        setupCuisinesChipsListener()
        setupUnwantedIngredientsChipsListener()

        setupOnNextButtonClick()

        return binding.root
    }

    private fun setupToggleButton() {
        binding.meatMaterialButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (group.checkedButtonId == R.id.meatEaterButton) {
                commonViewModel.saveIsVegetarian(false)
            } else if (group.checkedButtonId == R.id.vegetarianButton) {
                commonViewModel.saveIsVegetarian(true)
            }
        }
    }

    private fun setupCuisinesChipsListener() {
        binding.cuisinesChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val cuisinesList = mutableListOf<String>()
            for (id in checkedIds) {
                val view = group.findViewById<Chip>(id)
                cuisinesList.add(view.text.toString())
            }

            val cuisines = cuisinesList.joinToString(", ")
            commonViewModel.saveCuisines(cuisines)
        }
    }

    private fun setupUnwantedIngredientsChipsListener() {
        binding.unwantedIngredientsChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val ingredientsList = mutableListOf<String>()
            for (id in checkedIds) {
                val view = group.findViewById<Chip>(id)
                ingredientsList.add(view.text.toString())
            }

            val ingredients = ingredientsList.joinToString(", ")
            commonViewModel.saveUnwantedIngredients(ingredients)
        }
    }

    private fun setupOnNextButtonClick() {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_commonTasteFragment_to_filterFragment)
        }
    }

}