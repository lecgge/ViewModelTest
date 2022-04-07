package com.xu.viewmodeltest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    //模拟返回user数据
    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }
}