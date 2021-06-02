package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minEditText: EditText
    private lateinit var maxEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min = minEditText.text.toString()
            val max = maxEditText.text.toString()

            try {
                var minNumber = min.toInt()
                var maxNumber = max.toInt()

                if (minNumber > maxNumber) {
                    showError("The minimum number cannot be more than the maximum!")
                    return@setOnClickListener
                }

                (requireActivity() as? MainActivity)?.openSecondFragment(minNumber, maxNumber)
            } catch (nfe: NumberFormatException) {
               showError("Please enter whole numbers!")
            }
        }
    }

    private fun showError(str: String) {
        val errorDialogFragment = ErrorDialogFragment()
        errorDialogFragment.text = str
        val manager = requireActivity().supportFragmentManager
        errorDialogFragment.show(manager, "errorDialog")
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
    }
}