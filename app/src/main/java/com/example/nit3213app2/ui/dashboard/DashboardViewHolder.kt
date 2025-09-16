package com.example.nit3213app2.ui.dashboard

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213app2.R
import com.example.nit3213app2.data.dashboard.DashboardEntity
import com.example.nit3213app2.databinding.ItemLayoutRestfulApiDevBinding

/**
 * ViewHolder for displaying a single DashboardEntity item in the RecyclerView
 */
class DashboardViewHolder(
    private val binding: ItemLayoutRestfulApiDevBinding,
    private val onClick: (DashboardEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds a DashboardEntity to the item view
     */
    fun bind(item: DashboardEntity) {
        binding.itemTitleText.text = item.artworkTitle
        binding.itemArtistText.text =
            binding.root.context.getString(R.string.item_artist, item.artist)
        binding.itemYearText.text =
            binding.root.context.getString(R.string.item_year, item.year)
        binding.itemMediumText.text =
            binding.root.context.getString(R.string.item_medium, item.medium)

        // Show "More details" and navigation button only if description exists
        if (!item.description.isNullOrBlank()) {
            binding.detailsText.visibility = View.VISIBLE
            binding.navigationButton.visibility = View.VISIBLE
        } else {
            binding.detailsText.visibility = View.GONE
            binding.navigationButton.visibility = View.GONE
        }

        // Click only on button
        binding.navigationButton.setOnClickListener { onClick(item) }
    }
}