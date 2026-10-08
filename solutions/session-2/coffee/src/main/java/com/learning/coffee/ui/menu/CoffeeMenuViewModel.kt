package com.learning.coffee.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoffeeMenuViewModel : ViewModel() {

    private val menus = listOf("ลาเต้", "อเมริกาโน่", "มัทฉะลาเต้", "โกโก้")

    private val _recommendation = MutableLiveData<String>()
    val recommendation: LiveData<String>
        get() = _recommendation

    fun recommend() {
        _recommendation.value = menus.random()
    }
}
