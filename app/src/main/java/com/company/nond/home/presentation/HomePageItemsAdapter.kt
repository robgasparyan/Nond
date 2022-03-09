package com.company.nond.home.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.nond.databinding.ItemHomePageBinding
import com.company.nond.home.domain.HomePageItemsUIModel

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

    inner class ViewHolder(val binding: ItemHomePageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemContainer.setOnClickListener {
                onItemClicked?.invoke()
            }

        }

        fun bind(item: HomePageItemsUIModel) = with(binding) {
            val context = itemName.context
//            itemImage.loadImage("https://i.guim.co.uk/img/media/2a46ce0b584eaa648360df4f028309cae1f66573/0_338_7360_4417/master/7360.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=b706cbd0907bc2de5b8664d97975eb92")
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
