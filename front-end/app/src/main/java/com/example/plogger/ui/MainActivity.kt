package com.example.plogger.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.plogger.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.plogger.R
import com.example.plogger.databinding.ActivityMainBinding
import com.example.plogger.ui.community.CommunityFragment
import com.example.plogger.ui.jogging.JoggingFragment
import com.example.plogger.ui.myprofile.MyProfileFragment
import com.example.plogger.ui.ranking.RankingFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
//    val mainViewModel: MainViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferencesUtil.deleteToken()
    }

    private fun setUi() {
        supportFragmentManager.beginTransaction().add(R.id.tab_content, JoggingFragment()).commit()
        binding.tabs.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val transaction = supportFragmentManager.beginTransaction()
                    when (tab?.text) {
                        "조깅" -> transaction.replace(R.id.tab_content, JoggingFragment())
                        "커뮤니티" -> transaction.replace(R.id.tab_content, CommunityFragment())
                        "랭킹" -> transaction.replace(R.id.tab_content, RankingFragment())
                        "내정보" -> transaction.replace(R.id.tab_content, MyProfileFragment())
                    }
                    transaction.commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    Log.d("TabButton", "onTabUnselected...")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    Log.d("TabButton", "onTabReselected...")
                }
            }
        )
    }
}