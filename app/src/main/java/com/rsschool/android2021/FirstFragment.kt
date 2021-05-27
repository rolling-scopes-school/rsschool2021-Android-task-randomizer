package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(), NavigateToSecondFragment {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValueEditText: EditText? = null
    private var maxValueEditText: EditText? = null

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
        minValueEditText = view.findViewById(R.id.min_value)
        maxValueEditText = view.findViewById(R.id.max_value)


        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        var min: Int = MIN_NUMBER
        var max: Int = MAX_NUMBER

        minValueEditText?.addTextChangedListener {
            min = try {
                it.toString().toInt()
            } catch (e: Exception) {
                MIN_NUMBER
            }
        }

        maxValueEditText?.addTextChangedListener {
            max = try {
                it.toString().toInt()
            } catch (e: Exception) {
                MAX_NUMBER
            }
        }

        generateButton?.setOnClickListener {
            openSecondFragment(min, max)
        }
    }

    override fun openSecondFragment(min: Int, max: Int) {
        (activity as MainActivity).openSecondFragment(min, max)
    }

    companion object {

        private const val MIN_NUMBER = 0
        private const val MAX_NUMBER = Int.MAX_VALUE

        @JvmStatic
        fun newInstance(previousNumber: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousNumber)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}