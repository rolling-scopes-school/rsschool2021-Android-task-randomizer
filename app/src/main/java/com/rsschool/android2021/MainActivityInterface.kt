package com.rsschool.android2021

interface MainActivityInterface {
    fun moveToSecondFragment(min: Int, max: Int)
    fun setPreviousResult(string: Int)
    fun popBackstack()
}