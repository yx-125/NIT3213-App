package com.example.nit3213app2.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nit3213app2.databinding.FragmentDetailsBinding

/**
 * Fragment that displays the details of a selected dashboard entity
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        // Read arguments passed from DashboardFragment
        val args = requireArguments()
        val title = args.getString("title")
        val artist = args.getString("artist")
        val year = args.getString("year")
        val medium = args.getString("medium")
        val description = args.getString("description")

        // Populate UI with details
        binding.artworkTitle.text = title
        binding.artistValue.text = artist
        binding.yearValue.text = year
        binding.mediumValue.text = medium
        binding.descriptionValue.text = description

        return binding.root
    }

    /**
     * Clear binding reference when the view is destroyed to avoid memory leaks
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}