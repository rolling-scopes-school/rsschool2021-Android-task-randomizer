package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var listener: ActionPerformedListener? = null
    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValue: EditText? = null
    private var maxValue: EditText? = null
    private var min: Int? = null
    private var max: Int? = null

    // Добавляем слушатель через контекст
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionPerformedListener
    }

    // Создаем фрагмент используя inflater.inflate
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    // Создаем содержимое фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {

            // TODO: val min = ...
            min = minValue?.text.toString().toInt()
            // TODO: val max = ...
            max = maxValue?.text.toString().toInt()

//            if(minValue?.text?.isEmpty() as Boolean) {
//                minValue!!.error = "Введите минимальное значение."
//            } else
//            if(maxValue?.text?.isEmpty() as Boolean) {
//                previousResult?.text = "Введите максимальное значение."
//            } else
//                if (max!! < min!!) {}
            // previousResult?.text = "min $min and max $max"
            // TODO: send min and max to the SecondFragment
            listener?.onActionPerformedTwo(min as Int, max as Int)

        }
    }

    // Передаем данные в новый фрагмент, который создаст MainActivity
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