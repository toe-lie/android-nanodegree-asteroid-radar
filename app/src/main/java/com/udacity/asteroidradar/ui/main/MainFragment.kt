package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.MainFragmentBinding
import com.udacity.asteroidradar.ui.model.UiAsteroid
import com.udacity.asteroidradar.ui.model.UiAsteroidFilter
import com.udacity.asteroidradar.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding by autoCleared<MainFragmentBinding>()

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupUi()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupUi() {
        setupList()
    }

    private fun setupList() {
        binding.asteroidRecycler.adapter =
            AsteroidListAdapter(AsteroidListAdapter.OnClickListener { navigateToDetailScreen(it) })
    }

    private fun navigateToDetailScreen(asteroid: UiAsteroid) {
        findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter_week -> {
                viewModel.updateFilter(UiAsteroidFilter.Week)
                return true
            }
            R.id.action_filter_today -> {
                viewModel.updateFilter(UiAsteroidFilter.Today)
                return true
            }
            R.id.action_filter_saved -> {
                // TODO: extract string resource
                // Note to mentor: I can't find any requirement related with saved asteroids filter
                Toast.makeText(requireContext(), "Coming soon ...", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}