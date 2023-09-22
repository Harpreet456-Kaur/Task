package com.example.task

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        sharedPreferences = getSharedPreferences("count", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        binding.saveBtn.setOnClickListener {
            editor.putString("count", binding.count.text.toString())
            editor.apply()

            Log.d("TAG--->",binding.count.text.toString())


            val intent = Intent(this,MainList::class.java)
//            Log.e("TAG--->",intent.toString())
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}

