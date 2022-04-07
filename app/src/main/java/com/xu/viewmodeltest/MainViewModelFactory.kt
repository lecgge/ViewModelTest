package com.xu.viewmodeltest

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private var count:Int) : ViewModelProvider.Factory, Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        返回ViewModel实例
        return MainViewModel(count) as T
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainViewModelFactory> {
        override fun createFromParcel(parcel: Parcel): MainViewModelFactory {
            return MainViewModelFactory(parcel)
        }

        override fun newArray(size: Int): Array<MainViewModelFactory?> {
            return arrayOfNulls(size)
        }
    }

}