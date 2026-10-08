package com.learning.playground.lesson02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.playground.R
import com.learning.playground.databinding.ItemMenuBinding

// บทที่ 02 — Adapter ของรายการเมนู (ขั้นที่ 7) เขียนไว้แล้ว ไม่ต้องแก้
// Adapter คือตัวที่นำข้อมูลแต่ละชิ้นมาใส่ใน layout ของหนึ่งแถว วิธีเขียนอยู่ในบทที่ 11

class MenuAdapter(
    private var items: List<CoffeeItem> = emptyList()
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: CoffeeItem) {
            binding.tvItemName.text = item.name
            binding.tvItemPrice.text = binding.root.context.getString(R.string.menu_item_price, item.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.setData(items[position])
    }
}
