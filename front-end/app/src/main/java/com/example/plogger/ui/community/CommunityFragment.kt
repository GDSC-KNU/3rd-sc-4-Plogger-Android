package com.example.plogger.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plogger.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {
    lateinit var binding: FragmentCommunityBinding
//    private val communityViewModel: CommunityViewModel by viewModels()
    var isOpened = false
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

    }
}