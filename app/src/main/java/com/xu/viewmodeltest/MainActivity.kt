package com.xu.viewmodeltest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import androidx.core.content.edit
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xu.viewmodeltest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    var count1 = 0

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved",0)

        //ViewModelProvider获取viewmodel实例
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //通过ViewModelFactory获取viewmodel实例
        viewModel = ViewModelProvider(this,MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        binding.button.setOnClickListener {
//            count1 += 1
//            binding.count.text = count1.toString()
            //通过viewmodel实例获取数据
            viewModel.plusOne()
            refreshCounter()
        }
        binding.count.text = viewModel.counter.toString()
//        binding.count.text = count1.toString()

        binding.clearText.setOnClickListener {
            viewModel.clear()
            refreshCounter()
        }

        //当使用getUser方法传入userId，switchMap（）方法会监听到userId发生改变，接着调用Repository.getUser(userId)
        //返回一个可监听的userLiveData对象
        binding.addUser.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }
        //livedata监听数据是否发生变化，将数据显示在textview中
        viewModel.user.observe(this, Observer{ user -> binding.count.text = user.firstName})

        //count是定义在viewmodel中的livedata类型的数据，可以直接监听
        viewModel.counter.observe(this,
            Observer{ counts -> binding.count.text = counts.toString()})

        //lifecycle添加监听
        lifecycle.addObserver(MyObserver())
    }

    fun refreshCounter() {
        //更新页面
        binding.count.text = viewModel.counter.value.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }

    }
}