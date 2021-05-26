package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rsschool.android2021.databinding.FragmentSecondBinding

private const val MIN_VALUE_KEY = "MIN_VALUE"
private const val MAX_VALUE_KEY = "MAX_VALUE"

class SecondFragment : Fragment(R.layout.fragment_second) {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var mainActivityInterface: MainActivityInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityInterface) {
            mainActivityInterface = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        binding.result.text = (min..max).random().toString()

        binding.back.setOnClickListener {
            mainActivityInterface.popBackstack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val result = binding.result.text.toString().toIntOrNull()
        result?.let { mainActivityInterface.setPreviousResult(result) }
    }

    companion object {
        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()

            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args

            return fragment
        }
    }
}