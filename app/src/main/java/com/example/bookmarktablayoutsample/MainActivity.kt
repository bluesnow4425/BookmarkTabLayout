package com.example.bookmarktablayoutsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bluesnow4425.bookmarkTablayout.BookMarkTabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BookMarkTabLayout>(R.id.tabs).setOnItemSelectedListener {
            Toast.makeText(this,  "clicked"+it,Toast.LENGTH_SHORT).show()
        }
    }
}