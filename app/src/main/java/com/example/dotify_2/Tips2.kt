package com.example.dotify_2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class Tips2 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<TipsCard> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_2)

        setFullScreen(window)
        lightStatusBar(window)

        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, Tips::class.java)
            startActivity(intent)
        }
        /*list.addAll(CeritanyaDatabase.listTips)
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterTips(list)
        recyclerView.adapter = adapter*/
    }
}