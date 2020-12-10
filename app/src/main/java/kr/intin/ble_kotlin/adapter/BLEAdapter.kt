package kr.intin.ble_kotlin.adapter

import android.bluetooth.le.ScanResult
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.intin.ble_kotlin.databinding.ItemMainBinding
import kr.intin.ble_kotlin.viewmodel.MainViewModel

class BLEAdapter(private val model: MainViewModel) : RecyclerView.Adapter<BLEAdapter.BLEViewHolder>() {

    private val list = arrayListOf<ScanResult?>()
    private val TAG = "BLEAdapter"
    private lateinit var itemClickListner: ItemClickListener

    class BLEViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLEViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMainBinding = ItemMainBinding.inflate(layoutInflater, parent, false)
        return BLEViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BLEViewHolder, position: Int) {
        holder.binding.scanResult = list[position]

        holder.binding.item.setOnClickListener {
            itemClickListner.onClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addResult(result: ScanResult?) {

        if (list.isNotEmpty()) {
            (0 until list.size).forEach { i ->
                if (result?.device?.toString()?.equals(list[i]?.device?.toString()!!)!!) return
            }
        }
        list.add(result)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(scanResult: ScanResult?)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    fun clearList() {
        list.clear()
    }

    fun isListEmpty () : Boolean {
        return list.isEmpty()
    }
}

@BindingAdapter("rssi")
fun setRssi (textView: TextView, scanResult: ScanResult?) {
    textView.text = scanResult?.rssi?.toString()
}

@BindingAdapter("device")
fun setDevice (textView: TextView, scanResult: ScanResult?){
    textView.text = scanResult?.device?.toString()
}

@BindingAdapter("name")
fun setName (textView: TextView, scanResult: ScanResult?) {
    textView.text = scanResult?.device?.name
}
