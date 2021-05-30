package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception

class FirstFragment : Fragment() {


    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var listener: FirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FirstFragmentListener
    }

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

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val min = view.findViewById<EditText>(R.id.min_value)
        val max = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            minMaxAction(min.text.toString(), max.text.toString())
        }
    }

    private fun minMaxAction(minStr: String, maxStr: String) {
        var min: Int = 0
        var max: Int = 0
        try {
            minStr.toInt().also { min = it }
            max = maxStr.toInt()
        } catch (e: Exception) {
            Toast.makeText(context, "Please, input numbers", Toast.LENGTH_LONG).show()
            return
        }
        if (max < min) {
            Toast.makeText(context, "Max must be greater or equal min", Toast.LENGTH_LONG).show()
            return
        }
        if (max < 0 || min < 0) {
            Toast.makeText(context, "Numbers can't be negative", Toast.LENGTH_LONG).show()
            return
        }
        listener?.actionFirstFragment(min, max)
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

    interface FirstFragmentListener {
        fun actionFirstFragment(min: Int, max: Int)
    }
}