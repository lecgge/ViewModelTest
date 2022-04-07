package com.xu.viewmodeltest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import androidx.core.content.edit
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
            viewModel.count += 1
            refreshCounter()
        }
        binding.count.text = viewModel.count.toString()
//        binding.count.text = count1.toString()

        binding.clearText.setOnClickListener {
            viewModel.count = 0
            refreshCounter()
        }
    }

    fun refreshCounter() {
        //更新页面
        binding.count.text = viewModel.count.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.count)
        }

    }
}