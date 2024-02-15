package com.example.plogger.ui.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plogger.ApplicationClass.Companion.BOARD_ITEM
import com.example.plogger.databinding.FragmentCommunityBinding
import com.example.plogger.model.Board
import com.example.plogger.ui.adapter.BoardsAdapter

class CommunityFragment : Fragment() {
    lateinit var binding: FragmentCommunityBinding
//    private val communityViewModel: CommunityViewModel by viewModels()
    val boards = mutableListOf<Board>(
        Board(0, 0, "plogger","","14일 운동 일지","목표 시간: 30분...","",32,false,"2024.02.14"),
        Board(1, 0, "plogger","","15일 운동 일지","목표 시간: 1시간...","",32,false,"2024.02.15"),
        Board(2, 0, "plogger","","16일 운동 일지","목표 시간: 1시간 30분...","",32,false,"2024.02.16")
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()
        setAdapter(view)
    }

    override fun onResume() {
        super.onResume()
//        communityViewModel.getBoards()
    }

    private fun setUi() {

    }

    private fun setAdapter(view: View) {
        val boardsAdapter = BoardsAdapter()
        boardsAdapter.detailBoardListener =
            object : BoardsAdapter.DetailBoardListener {
                override fun onClick(board: Board) {
                    val intent = Intent(activity, BoardDetailActivity::class.java)
                    intent.putExtra(BOARD_ITEM, board)
                    startActivity(intent)
                }
            }

        binding.boardRv.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = boardsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    view.context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        boardsAdapter.submitList(boards)
//        communityViewModel.boards.observe(viewLifecycleOwner) {
//            boardsAdapter.submitList(it)
//        }
    }
}