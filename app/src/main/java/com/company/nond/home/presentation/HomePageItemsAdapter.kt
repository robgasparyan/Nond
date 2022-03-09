package com.company.nond.home.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.nond.databinding.ItemHomePageBinding
import com.company.nond.home.domain.HomePageItemsUIModel
import com.end.nond.extensions.loadImage

class HomePageItemsAdapter:RecyclerView.Adapter<HomePageItemsAdapter.ViewHolder>() {

    private var data = mutableListOf<HomePageItemsUIModel>()
    var onItemClicked: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    inner class ViewHolder(private val binding: ItemHomePageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemContainer.setOnClickListener {
                onItemClicked?.invoke()
            }
        }

        fun bind(item: HomePageItemsUIModel) = with(binding) {
            item.image_urls?.let {
                itemImage.loadImage(it[0])
            }
            itemName.text = item.name
            itemPrice.text = item.price
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<HomePageItemsUIModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun updateItem(dto: HomePageItemsUIModel) {
        val position = data.indexOf(dto)
        if (position == RecyclerView.NO_POSITION) return

        data[position] = dto
        notifyItemChanged(position)
    }

    private fun getItem(position: Int): HomePageItemsUIModel? {
        return data.getOrNull(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
