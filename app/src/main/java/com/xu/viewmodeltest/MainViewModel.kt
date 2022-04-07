package com.xu.viewmodeltest

import androidx.lifecycle.ViewModel

class MainViewModel(var count1: Int) : ViewModel() {
    var count = count1
}