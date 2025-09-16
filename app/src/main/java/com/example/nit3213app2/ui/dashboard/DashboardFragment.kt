package com.example.nit3213app2.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.nit3213app2.R
import com.example.nit3213app2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val vm: DashboardViewModel by viewModels()
    private lateinit var adapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        // Set up RecyclerView
        adapter = DashboardAdapter { entity ->
            val args = Bundle().apply {
                putString("title", entity.artworkTitle)
                putString("artist", entity.artist)
                putString("year", entity.year.toString())
                putString("medium", entity.medium)
                putString("description", entity.description)
            }
            findNavController().navigate(R.id.action_dashboardFragment_to_detailsFragment, args)
        }
        binding.recyclerView.adapter = adapter

        // Get keypass passed from LoginFragment
        val keypass = arguments?.getString("keypass")
        if (!keypass.isNullOrEmpty()) {
            vm.loadDashboard(keypass)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    vm.items.collectLatest { adapter.submitList(it) }
                }
                launch {
                    vm.error.collectLatest { msg ->
                        if (msg != null) {
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}