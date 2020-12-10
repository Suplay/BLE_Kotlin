package kr.intin.ble_kotlin.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.intin.ble_kotlin.databinding.ItemReceivemsgBinding
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){

    private val response = arrayListOf<String>()

    class ChatViewHolder (val binding: ItemReceivemsgBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemReceivemsgBinding = ItemReceivemsgBinding.inflate(layoutInflater, parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        holder.binding.chatTime = response[position]
    }

    override fun getItemCount(): Int {
        return response.size
    }

    fun addResponse (s : String) {
        response.add(s)
        notifyDataSetChanged()
    }
}

@BindingAdapter("chatTime")
fun setChatTime(textView: TextView, s : String) {
    textView.text = s
}