package com.mazurets.guessthebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mazurets.guessthebox.databinding.ActivityBoxBinding
import com.mazurets.guessthebox.databinding.ActivityOpenBoxBinding

class OpenBoxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpenBoxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenBoxBinding.inflate(layoutInflater).also { setContentView(it.root) }


    }

    companion object {
        @JvmStatic
        val EXTRA_OPTIONS = "EXTRA_OPTIONS"
        @JvmStatic
        private val KEY_INDEX = "KEY_INDEX"
        @JvmStatic
        private val KEY_START_TIMESTAMP = "KEY_START_TIMESTAMP"
        @JvmStatic
        private val TIMER_DURATION = 10_000L
    }
}