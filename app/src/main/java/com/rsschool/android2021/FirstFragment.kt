package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
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

        with(binding) {
            minValue.afterTextChanged {
                if (it.isNotEmpty()) {
                    binding.generate.isEnabled = isFormValid()
                }
            }

            maxValue.afterTextChanged {
                if (it.isNotEmpty()) {
                    binding.generate.isEnabled = isFormValid()
                }
            }

            generate.setOnClickListener {
                if (isFormValid()) {
                    val min = binding.minValue.text.toString().toInt()
                    val max = binding.maxValue.text.toString().toInt()
                    mainActivityInterface.moveToSecondFragment(min, max)
                }
            }
        }
    }

    override fun setPreviousResult(prevResult: Int) {
        with(binding) {
            binding.generate.isEnabled = false
            minValue.text.clear()
            maxValue.text.clear()
            previousResult.text = resources.getString(R.string.prevResult, prevResult)
        }
    }

    private fun isFormValid() = validateMinField() && validateMaxField() && checkForm()

    private fun validateMinField(): Boolean {
        var isMinValid = false
        val minStr = binding.minValue.text.toString()
        binding.minValue.error = if (minStr.isEmpty()) {
            "Empty field"
        } else {
            try {
                minStr.toInt()
                isMinValid = true
                null
            } catch (e: Exception) {
                "Min must be less than 2 147 483 647"
            }
        }

        return isMinValid
    }

    private fun validateMaxField(): Boolean {
        var isMaxValid = false
        val maxStr = binding.maxValue.text.toString()
        binding.maxValue.error = if (maxStr.isEmpty()) {
            "Empty field"
        } else {
            try {
                maxStr.toInt()
                isMaxValid = true
                null
            } catch (e: Exception) {
                "Max must be less than 2 147 483 647"
            }
        }
        return isMaxValid
    }

    private fun checkForm(): Boolean {
        var isValid = true

        val min = binding.minValue.text.toString().toInt()
        val max = binding.maxValue.text.toString().toInt()
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