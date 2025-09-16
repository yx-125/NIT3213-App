package com.example.nit3213app2.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nit3213app2.data.dashboard.DashboardEntity
import com.example.nit3213app2.databinding.ItemLayoutRestfulApiDevBinding

/**
 * RecyclerView adapter for displaying a list of dashboard entities
 */
class DashboardAdapter(
    private val onClick: (DashboardEntity) -> Unit
) : ListAdapter<DashboardEntity, DashboardViewHolder>(DiffCallback()) {

    /**
     * Creates a new DashboardViewHolder by inflating the item layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutRestfulApiDevBinding.inflate(inflater, parent, false)
        return DashboardViewHolder(binding, onClick)
    }

    /**
     * Binds a DashboardEntity to the given DashboardViewHolder
     */
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * DiffUtil implementation for comparing DashboardEntity items
     * Ensures RecyclerView only updates items that have changed
     */
    class DiffCallback : DiffUtil.ItemCallback<DashboardEntity>() {
        override fun areItemsTheSame(oldItem: DashboardEntity, newItem: DashboardEntity) =
            oldItem.artworkTitle == newItem.artworkTitle

        override fun areContentsTheSame(oldItem: DashboardEntity, newItem: DashboardEntity) =
            oldItem == newItem
    }
}