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

class SecondFragment : Fragment() {

    private var listener: ActionPerformedListener? = null
    private var backButton: Button? = null
    private var result: TextView? = null

    // Добавляем слушатель через контекст
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionPerformedListener
    }

    // Создаем фрагмент, переопределив метод и используя inflater.inflate
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    // Создаем содержимое фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        val resultRandom = generate(min, max)
        result?.text = resultRandom.toString()

        backButton?.setOnClickListener {
            // Вызываем первый фрагмент, используя метод, который принадлежит объекту MainActivity
            listener?.onActionPerformedOne(resultRandom)

        }
    }

    private fun generate(min: Int, max: Int): Int {
        // Для генерации случайного числа, перем готовый метод из библиотеки
        return Random.nextInt(from = min, until = max)
    }

    // Передаем данные в новый фрагмент, который создаст MainActivity
    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()

            // Полученные значения связываем парой <ключ, значение> и кладем в объект хранения
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}