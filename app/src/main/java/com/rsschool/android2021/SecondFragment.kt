package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment(), NavigateToFirstFragment {

    private var backButton: Button? = null
    private var result: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            openFirstFragment(result?.text.toString().toInt())
        }
    }

    override fun openFirstFragment(previousNumber: Int) {
        (activity as MainActivity).openFirstFragment(previousNumber)
    }

    private fun generate(min: Int, max: Int): Int = Random.nextInt(min, max)

    companion object {

        @JvmStatic
        fun newInstance(minNumber: Int, maxNumber: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, minNumber)
            args.putInt(MAX_VALUE_KEY, maxNumber)
            fragment.arguments = args
            return fragment
        }

        const val MIN_VALUE_KEY = "MIN_VALUE"
        const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}