package com.xu.viewmodeltest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xu.viewmodeltest.entity.User

class MainViewModel(count1: Int) : ViewModel() {
    private val userIdLiveData = MutableLiveData<String>()

    //将viewmodel中的数据
    val counter: LiveData<Int>
            get() = _counter
    private val _counter = MutableLiveData<Int>()
    init {
        _counter.value = count1
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    //使用switchMap（）方法返回泛型为User的可监听的livedata对象
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) {
        userId -> Repository.getUser(userId)
    }


    fun getUser(userId : String){
        userIdLiveData.value = userId
    }
}