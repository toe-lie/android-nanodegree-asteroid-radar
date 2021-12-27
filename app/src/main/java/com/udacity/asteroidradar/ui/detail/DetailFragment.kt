package com.udacity.asteroidradar.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.DetailFragmentBinding
import com.udacity.asteroidradar.util.autoCleared

class DetailFragment : Fragment() {

  private var binding by autoCleared<DetailFragmentBinding>()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    binding = DetailFragmentBinding.inflate(inflater)
    binding.lifecycleOwner = this

    val asteroid = DetailFragmentArgs.fromBundle(arguments!!).selectedAsteroid

    binding.asteroid = asteroid

    binding.helpButton.setOnClickListener { displayAstronomicalUnitExplanationDialog() }

    return binding.root
  }

  private fun displayAstronomicalUnitExplanationDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_MaterialComponents_Light)
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
            .show()
  }
}
