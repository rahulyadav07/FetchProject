package com.rahulyadav.fetchproject.ui.activity.fetchactivity


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulyadav.fetchproject.data.model.HiringData
import com.rahulyadav.fetchproject.databinding.FetchItemsBinding

class FetchAdapter(private val hiringList: ArrayList<HiringData>): RecyclerView.Adapter<FetchAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding:FetchItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(hiringData: HiringData) {
            binding.apply {
                hiringId.text = hiringData.hiringId?.toString() ?: ""
                hiringListId.text = hiringData.hiringListId?.toString() ?: ""
                hiringName.text = hiringData.hiringName ?: ""
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder = DataViewHolder(
        FetchItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(hiringList[position])
    }

    override fun getItemCount(): Int = hiringList.size


    fun addData(list :List<HiringData>) {
        hiringList.clear() // clear the list and add filtered list here
        hiringList.addAll(list)
    }

}