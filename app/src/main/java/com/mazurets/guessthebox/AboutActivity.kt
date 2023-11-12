package com.mazurets.guessthebox

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mazurets.guessthebox.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.aboutTextApplicationVersionText.text = Build.getRadioVersion()
        binding.aboutTextApplicationVersionCodeText.text = Build.VERSION_CODES.BASE.toString()

        binding.aboutBtnOkBaton.setOnClickListener {
            onOkPressed()
        }
    }

    private fun onOkPressed() {
        finish()
    }
}