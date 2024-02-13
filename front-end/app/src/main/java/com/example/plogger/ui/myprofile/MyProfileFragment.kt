package com.example.plogger.ui.myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plogger.databinding.FragmentMyprofileBinding

class MyProfileFragment : Fragment() {
    lateinit var binding: FragmentMyprofileBinding
//    private val myProfileViewModel: MyProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyprofileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()
    }

    override fun onResume() {
        super.onResume()
//        myProfileViewModel.getMyProfile()
//        myProfileViewModel.getMyAllChallenge()
//        myProfileViewModel.getMyBoards()
    }

    private fun setUi() {

    }
}