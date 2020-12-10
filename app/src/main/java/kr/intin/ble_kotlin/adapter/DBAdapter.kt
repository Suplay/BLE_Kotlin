package kr.intin.ble_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.intin.ble_kotlin.data.entity.UseTime
import kr.intin.ble_kotlin.databinding.ItemDbBinding

class DBAdapter : RecyclerView.Adapter<DBAdapter.DBViewHolder>(){

    var list = arrayListOf<UseTime>()

    class DBViewHolder(val binding: ItemDbBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDbBinding.inflate(layoutInflater, parent, false)
        return DBViewHolder(binding)

    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        holder.binding.db = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}