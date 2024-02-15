package com.example.plogger.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plogger.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.plogger.R
import com.example.plogger.databinding.ItemBoardBinding
import com.example.plogger.model.Board
import com.example.plogger.ui.util.Util.loadImg

class BoardsAdapter : ListAdapter<Board, BoardsAdapter.BoardsViewHolder>(
    BoardAllDiffCallback()
) {
    private lateinit var binding: ItemBoardBinding

    inner class BoardsViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.Q)
        fun setBind(board: Board) {
            binding.apply {
                if (board.profileImage != null && board.profileImage != "") {
                    loadImg(this.root.context, board.profileImage, profileImg)
                } else {
                    profileImg.setImageResource(R.drawable.leaf_character)
                }
                profileNickname.text = board.nickname
                boardDate.text = board.createdDate.substring(0, 4) +
                        "." + board.createdDate.substring(5, 7) +
                        "." + board.createdDate.substring(8, 10)
                boardDetailBtn.setOnClickListener {
                    detailBoardListener.onClick(board = board)
                }
                if (board.boardImage != null && board.boardImage != "") {
                    loadImg(this.root.context, board.boardImage, boardImg)
                }
                boardContent.text = board.boardContent
                root.setOnClickListener {
                    detailBoardListener.onClick(board = board)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardsViewHolder {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: BoardsViewHolder, position: Int) {
        holder.setBind(getItem(position))
    }

    interface DetailBoardListener {
        fun onClick(board: Board)
    }

    lateinit var detailBoardListener: DetailBoardListener
}

class BoardAllDiffCallback : DiffUtil.ItemCallback<Board>() {
    override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem.boardId == newItem.boardId
    }

    override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem == newItem
    }
}