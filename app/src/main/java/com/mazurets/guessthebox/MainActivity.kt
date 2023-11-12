package com.mazurets.guessthebox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mazurets.guessthebox.databinding.ActivityMainBinding
import com.mazurets.guessthebox.model.Options

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var options: Options

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.mainBtnOpenBox.setOnClickListener { onOpenBoxPressed() }
        binding.mainBtnOptions.setOnClickListener { onOptionsPressed() }
        binding.mainBtnAbout.setOnClickListener { onAboutPressed() }
        binding.mainBtnExit.setOnClickListener { onExitPressed() }

        options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OPTIONS_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            options = data?.getParcelableExtra(OptionsActivity.EXTRA_OPTIONS) ?:
                throw IllegalArgumentException("Can't get the updated data from options activity")
        }
    }

    private fun onOpenBoxPressed() {
        val intent = Intent(this, OpenBoxActivity::class.java)
        intent.putExtra(OpenBoxActivity.EXTRA_OPTIONS, options)
        startActivity(intent)
    }

    private fun onOptionsPressed() {
        val intent = Intent(this, OptionsActivity::class.java)
        intent.putExtra(OptionsActivity.EXTRA_OPTIONS, options)
        startActivityForResult(intent, OPTIONS_REQUEST_CODE)

    }

    private fun onAboutPressed() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun onExitPressed() {
        finish()
    }

    companion object {
        @JvmStatic
        private val KEY_OPTIONS = "OPTIONS"
        @JvmStatic
        private val OPTIONS_REQUEST_CODE = 1
    }
}