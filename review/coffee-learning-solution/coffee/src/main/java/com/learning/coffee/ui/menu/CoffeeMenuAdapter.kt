// ผู้เรียนสร้างไฟล์นี้เอง: Session 5 · ภารกิจ 3
// คู่มือให้โครงของ class มา ผู้เรียนเขียน 3 จุดที่กำกับไว้ด้านล่าง
package com.learning.coffee.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.apilayer.repository.coffee.CoffeeMenuItem
import com.learning.coffee.R
import com.learning.coffee.databinding.ItemCoffeeMenuBinding
import com.learning.core.extension.singleClick

class CoffeeMenuAdapter(
    private var items: List<CoffeeMenuItem> = emptyList(),
    private val onItemClick: (CoffeeMenuItem) -> Unit = {}
) : RecyclerView.Adapter<CoffeeMenuAdapter.CoffeeMenuViewHolder>() {

    inner class CoffeeMenuViewHolder(
        private val binding: ItemCoffeeMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: CoffeeMenuItem) {
            // >>> ผู้เรียนเขียน: Session 5 · ภารกิจ 3
            binding.tvItemName.text = item.name.orEmpty()
            binding.tvItemPrice.text = binding.root.context.getString(R.string.coffee_price, item.price ?: 0)
            binding.root.singleClick { onItemClick(item) }
            // <<< จบส่วนที่ผู้เรียนเขียน
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeMenuViewHolder {
        val binding = ItemCoffeeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeMenuViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size // ผู้เรียนเขียน: Session 5 · ภารกิจ 3

    override fun onBindViewHolder(holder: CoffeeMenuViewHolder, position: Int) {
        holder.setData(items[position]) // ผู้เรียนเขียน: Session 5 · ภารกิจ 3
    }

    fun updateItems(newItems: List<CoffeeMenuItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
