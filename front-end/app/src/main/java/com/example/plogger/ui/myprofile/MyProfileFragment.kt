package com.example.plogger.ui.myprofile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plogger.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.plogger.databinding.FragmentMyprofileBinding
import com.example.plogger.ui.user.LoginActivity

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
        binding.apply {

            // 포리필 편집
            box3.setOnClickListener {
                startActivity(Intent(activity, EditProfileActivity::class.java))
            }
            editProfileBtn.setOnClickListener {
                startActivity(Intent(activity, EditProfileActivity::class.java))
            }

            // 오픈소스 라이센스
            box4.setOnClickListener {

            }

            // 로그아웃
            box6.setOnClickListener {
//                sharedPreferencesUtil.deleteToken()
                startActivity(Intent(activity, LoginActivity::class.java))
                activity?.finish()
            }
        }
    }
}