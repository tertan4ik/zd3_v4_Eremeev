package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.room.Room

class ListActivity : AppCompatActivity() {
    private lateinit var listContainer: LinearLayout
    private lateinit var database: AppDatabase
    private val items = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listContainer = findViewById(R.id.listContainer)
        database = Room.databaseBuilder(this, AppDatabase::class.java, "app_database")
            .allowMainThreadQueries().build()
        loadItemsFromDatabase()
        updateList()
    }
    private fun loadItemsFromDatabase() {
        val itemList = database.itemDao().getAll()
        items.clear()
        for (item in itemList) {
            if (!items.contains(item.name)) {
                items.add(item.name)
            }
        }
    }
    private fun updateList() {
        listContainer.removeAllViews()
        for (item in items) {
            val textView = TextView(this)
            textView.text = item
            textView.setTextColor(resources.getColor(android.R.color.white))
            textView.textSize = 18f
            listContainer.addView(textView)
        }
    }
}


