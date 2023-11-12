package com.mazurets.guessthebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OpenBoxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_box)
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