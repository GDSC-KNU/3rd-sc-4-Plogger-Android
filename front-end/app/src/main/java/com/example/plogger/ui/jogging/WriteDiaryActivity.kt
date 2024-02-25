package com.example.plogger.ui.jogging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plogger.databinding.ActivityWriteDiaryBinding

class WriteDiaryActivity : AppCompatActivity() {
    lateinit var binding: ActivityWriteDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }

            recordBtn.setOnClickListener {
                finish()
            }
        }
    }
}