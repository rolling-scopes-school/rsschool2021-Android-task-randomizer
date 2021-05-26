package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rsschool.android2021.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first), FirstFragmentInterface {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var mainActivityInterface: MainActivityInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityInterface) {
            mainActivityInterface = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding.generate.setOnClickListener {
            val minStr = binding.minValue.text.toString()
            val maxStr = binding.maxValue.text.toString()

            if (checkIsNotEmpty(minStr, maxStr)) {
                val min = minStr.toIntOrNull() ?: 0
                val max = maxStr.toIntOrNull() ?: 0

                if (checkIsValid(min, max)) {
                    mainActivityInterface.moveToSecondFragment(min, max)
                }
            }
        }
    }

    override fun setPreviousResult(prevResult: Int) {
        binding.minValue.text.clear()
        binding.maxValue.text.clear()
        binding.previousResult.text = resources.getString(R.string.prevResult, prevResult)
    }

    private fun checkIsNotEmpty(minStr: String, maxStr: String): Boolean {
        var isEmpty = true

        binding.minValue.error = if (minStr.isEmpty()) {
            isEmpty = false
            "Empty field"
        } else null

        binding.maxValue.error = if (maxStr.isEmpty()) {
            isEmpty = false
            "Empty field"
        } else null

        return isEmpty
    }

    private fun checkIsValid(min: Int, max: Int): Boolean {
        var isValid = true
        if (min > max) {
            binding.minValue.error = "Invalid input data: Min > Max!"
            binding.maxValue.error = "Invalid input data: Min > Max!"
            isValid = false
        } else {
            binding.minValue.error = null
            binding.maxValue.error = null
        }
        return isValid
    }
}