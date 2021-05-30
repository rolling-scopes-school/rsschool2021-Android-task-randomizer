package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment() : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var listener: SecondFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SecondFragmentListener
    }

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
        val min = arguments?.getInt("min")
        val max = arguments?.getInt("max")
        result?.text = generate(min!!, max!!).toString()
        backButton?.setOnClickListener {
            listener?.back()
        }
    }

    private fun generate(min: Int, max: Int): Int {
        return (min..max).random()
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            fragment.arguments = Bundle()
            fragment.arguments?.putInt("min", min)
            fragment.arguments?.putInt("max", max)
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }

    interface SecondFragmentListener {
        fun back()
    }
}