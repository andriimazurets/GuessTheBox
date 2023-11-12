package com.mazurets.guessthebox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.mazurets.guessthebox.databinding.ActivityOptionsBinding
import com.mazurets.guessthebox.model.Options
import java.lang.IllegalArgumentException

class OptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsBinding
    private lateinit var options: Options

    private lateinit var boxCountItems: List<BoxCountItem>
    private lateinit var adapter: ArrayAdapter<BoxCountItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        options = savedInstanceState?.getParcelable<Options>(KEY_OPTIONS) ?:
        intent.getParcelableExtra(EXTRA_OPTIONS) ?:
        throw IllegalArgumentException ("You need to specify EXTRA_OPTIONS argument to launch this activity")

        setupCheckBox()
        setupSpinner()
        updateUi()

        binding.optionsBtnCancel.setOnClickListener {
            onCancelPressed()
        }
        binding.optionsBtnConfirm.setOnClickListener {
            onConfirmPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    private fun setupSpinner () {
        boxCountItems = (1..6).map { BoxCountItem(it, "$it boxes") }

        adapter = ArrayAdapter(
            this,
            R.layout.item_spinner,
            boxCountItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

        binding.optionsSpinner.adapter = adapter
        binding.optionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val count = boxCountItems[position].count
                options = options.copy(boxCount = count)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateUi() {
        binding.optionsCheckBox.isChecked = options.isTimerEnabled

        val currentIndex = boxCountItems.indexOfFirst { it.count == options.boxCount }
        binding.optionsSpinner.setSelection(currentIndex)
    }

    private fun setupCheckBox () {
        binding.optionsCheckBox.setOnClickListener {
            options = options.copy(isTimerEnabled = binding.optionsCheckBox.isChecked)
        }
    }

    private fun onCancelPressed() {
        finish()
    }

    private fun onConfirmPressed () {
        val intent = Intent()
        intent.putExtra(EXTRA_OPTIONS, options)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        @JvmStatic
        val EXTRA_OPTIONS = "EXTRA_OPTIONS"
        @JvmStatic
        private val KEY_OPTIONS = "KEY_OPTIONS"
    }

    class BoxCountItem(val count: Int, private val optionsTitle: String) {
        override fun toString(): String {
            return optionsTitle
        }
    }
}