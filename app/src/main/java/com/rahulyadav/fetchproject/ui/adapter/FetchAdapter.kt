package com.rahulyadav.fetchproject.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulyadav.fetchproject.data.model.HiringData
import com.rahulyadav.fetchproject.databinding.FetchItemsBinding
import com.rahulyadav.fetchproject.databinding.HeaderItemBinding

class FetchAdapter(private val hiringList: ArrayList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    // ViewHolder for data items
    class DataViewHolder(private val binding: FetchItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hiringData: HiringData) {
            binding.apply {
                hiringId.text = "ID:- ".plus(hiringData.hiringId?.toString() ?: "")
                hiringName.text = "Hiring Name: - ".plus(hiringData.hiringName ?: "")
            }
        }
    }

    // ViewHolder for headers
    class HeaderViewHolder(private val binding: HeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(headerText: String) {
            binding.headerListId.text = headerText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            HeaderViewHolder(HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            DataViewHolder(FetchItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(hiringList[position] as String)
        } else if (holder is DataViewHolder) {
            holder.bind(hiringList[position] as HiringData)
        }
    }

    override fun getItemCount(): Int = hiringList.size

    override fun getItemViewType(position: Int): Int {
        return if (hiringList[position] is String) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun addData(list: List<HiringData>) {
        hiringList.clear()

        // First, sort the list by hiringListId and name
        val sortedList = list
            .sortedWith(compareBy({ it.hiringListId }, { it.hiringId }))

        // Group the sorted list by hiringListId
        val groupedData = sortedList.groupBy { it.hiringListId }

        // Flatten the grouped data with headers
        for ((hiringListId, items) in groupedData) {
            hiringList.add(hiringListId.toString()) // Header (hiringListId)
            hiringList.addAll(items) // Add items under the header
        }
    }

}
