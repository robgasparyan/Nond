package com.company.nond

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.nond.databinding.ActivityMainBinding
import com.company.nond.utils.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun showLoading() = binding.progress.show()

    fun hideLoading() = binding.progress.hide()
}