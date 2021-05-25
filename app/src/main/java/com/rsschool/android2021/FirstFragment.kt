package com.rsschool.android2021

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException

class FirstFragment : Fragment() {
    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var maxNumberEditText: EditText? = null
    private var minNumberEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)

        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        maxNumberEditText = view.findViewById(R.id.max_value)
        minNumberEditText = view.findViewById(R.id.min_value)

        previousResult?.text = "Previous result: ${result.toString()}"
        generateButton?.isEnabled = false

        maxNumberEditText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validate()
            }
        })
        minNumberEditText?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validate()
            }
        })

        generateButton?.setOnClickListener {
            val min = minNumberEditText?.text?.toString()?.toInt() ?: 0
            val max = maxNumberEditText?.text?.toString()?.toInt() ?: 0

            (activity as OnGenerateClicked).onGenerateClicked(min, max)
        }
    }

    private fun validate(): Unit {
        val maxNumText = maxNumberEditText?.text
        val minNumText = minNumberEditText?.text

        if ( maxNumText?.isNotBlank() == true && minNumText?.isNotBlank() == true ) {
            try {
                val maxNumInt = maxNumText.toString().toInt()
                val minNumInt = minNumText.toString().toInt()

                generateButton?.isEnabled = maxNumInt > 0 && minNumInt > 0 && maxNumInt > minNumInt
            } catch ( e: NumberFormatException ) {
                val snack = Snackbar.make(view as View, R.string.number_too_big_toast,
                    Snackbar.LENGTH_LONG)
                    .setAction("x") { }
                val snackView = snack.view
                val params = snackView.layoutParams as FrameLayout.LayoutParams

                params.gravity = Gravity.TOP
                snackView.layoutParams = params
                snack.show()
                generateButton?.isEnabled = false
            }
        } else {
            generateButton?.isEnabled = false
        }
    }

    interface OnGenerateClicked {
        fun onGenerateClicked(min: Int, max: Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()

            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
        private const val TAG = "FirstFragment"
    }
}