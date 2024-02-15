package com.example.plogger.ui.community

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.plogger.ApplicationClass.Companion.BOARD_ITEM
import com.example.plogger.R
import com.example.plogger.databinding.ActivityBoardDetailBinding
import com.example.plogger.model.Board
import com.example.plogger.model.Chat
import com.example.plogger.ui.adapter.ChatsAdapter
import com.example.plogger.ui.util.Util.hideKeyboard
import com.example.plogger.ui.util.Util.loadImg

class BoardDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding
    lateinit var board: Board
    val chatsAdapter = ChatsAdapter()
    //    private val boardDetailViewModel: BoardDetailViewModel by viewModels()
    var chats = mutableListOf<Chat>(
        Chat(0, 0, "plogger", "", "멋져요! bb"),
        Chat(1, 1, "plo", "", "훌륭!!"),
        Chat(2, 2, "plog", "", "강추!")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        board = intent.getSerializableExtra(BOARD_ITEM) as Board
//        boardDetailViewModel.getChats(board.boardId)

        setUi()
        setAdapter()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            // 게시글 작성자의 프로필 데이터 설정
            if (board.profileImage != null && board.profileImage != "") {
                loadImg(this.root.context, board.profileImage, profileImg)
            } else {
                profileImg.setImageResource(R.drawable.leaf_character)
            }
            profileNickname.text = board.nickname
            // 게시글 좋아요 버튼 이미지 설정

            // 게시글 좋아요 버튼 컨트롤


            // 게시글 데이터 설정
            if (board.boardImage != null && board.boardImage != "") {
                loadImg(this.root.context, board.boardImage, boardImg)
            }
            boardDate.text = board.createdDate.substring(0, 4) +
                    "." + board.createdDate.substring(5, 7) +
                    "." + board.createdDate.substring(8, 10)
            boardTitle.text = board.boardTitle
            boardContent.text = board.boardContent
            likeNum.text = board.likeCnt.toString()
            chatNum.text = chats.size.toString()
            // 댓글 추가 컨트롤
            chatSendBtn.setOnClickListener {
//                boardDetailViewModel.postComment(
//                    board.boardId,
//                    chatEditText.text.toString()
//                )

                chats.add(Chat(chats.size, 0, "plogger", "", chatEditText.text.toString()))
                chatsAdapter.notifyDataSetChanged()
                chatEditText.text.clear()
                hideKeyboard(this@BoardDetailActivity)
            }
        }
    }

    private fun setAdapter() {
//        val chatsAdapter = ChatsAdapter()

        binding.chatRv.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = chatsAdapter
        }

        chatsAdapter.submitList(chats)
//        boardDetailViewModel.comments.observe(this) {
//            commentsAdapter.submitList(it.toMutableList())
//        }
    }
}