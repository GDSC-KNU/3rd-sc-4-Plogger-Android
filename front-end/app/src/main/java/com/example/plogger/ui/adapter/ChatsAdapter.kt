package com.example.plogger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.plogger.R
import com.example.plogger.databinding.ItemChatBinding
import com.example.plogger.model.Chat
import com.example.plogger.ui.util.Util

class ChatsAdapter :
    ListAdapter<Chat, ChatsAdapter.ChatsViewHolder>(
    BoardChatDiffCallback()
) {
    private lateinit var binding: ItemChatBinding

    inner class ChatsViewHolder(private val binding: ItemChatBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun setBind(chat: Chat) {
            binding.apply {
                if (chat.profileImage != null && chat.profileImage != "") {
                    Util.loadImg(this.root.context, chat.profileImage, profileImg)
                } else {
                    profileImg.setImageResource(R.drawable.leaf_character)
                }
                profileNickname.text = chat.nickname
                chatContent.text = chat.content
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }
}

class BoardChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.chatId == newItem.chatId
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }
}