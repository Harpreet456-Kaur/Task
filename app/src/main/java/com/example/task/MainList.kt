package com.example.task

import android.app.Dialog
import android.content.ClipData.Item
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import com.example.task.databinding.ActivityMainListBinding
import com.example.task.databinding.AddBinding
import com.example.task.databinding.EditBinding

class MainList : AppCompatActivity() {
    lateinit var binding: ActivityMainListBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var adapter: ArrayAdapter<Int>
    var list = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list as List<Int>)
        binding.list.adapter = adapter

        binding.list.setOnItemClickListener { _, _, position, _ ->

            var dialog = Dialog(this)
            var dialogBoxBinding = EditBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBoxBinding.root)

            dialogBoxBinding.btnEdit.setOnClickListener {
                var new = dialogBoxBinding.num.text.toString()
                list.set(position, new.toInt())
                adapter.notifyDataSetChanged()
                dialog.dismiss()
                Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
            }

            dialogBoxBinding.btnDelete.setOnClickListener {
                list.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            dialog.show()
            Log.d("TAG--->", "Clicked")
            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
        }
        getList()

        //add number
        binding.floatingBtn.setOnClickListener {
            var newDialog = Dialog(this)
            var newDialogBinding = AddBinding.inflate(layoutInflater)
            newDialog.setContentView(newDialogBinding.root)

            newDialogBinding.addBtn.setOnClickListener {
                var item = newDialogBinding.name.text.toString()
                list.add(list.size, item.toInt())
                adapter.notifyDataSetChanged()
                newDialog.dismiss()
            }
            newDialog.show()
        }
        setContentView(binding.root)
    }

    fun getList() {
        sharedPreferences = getSharedPreferences("count", MODE_PRIVATE)
        val num = sharedPreferences.getString("count", "")
        Log.d("TAG--->",num.toString())
        for (i in 1..num!!.toInt()) {
            list.add(i)
        }
    }
}