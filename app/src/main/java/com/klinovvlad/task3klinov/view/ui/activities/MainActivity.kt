package com.klinovvlad.task3klinov.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klinovvlad.task3klinov.R
import com.klinovvlad.task3klinov.databinding.ActivityMainBinding
import com.klinovvlad.task3klinov.view.ui.fragments.FirstScreenFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, FirstScreenFragment())
            .commit()
    }
}