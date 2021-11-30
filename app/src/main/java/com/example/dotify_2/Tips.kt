package com.example.dotify_2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotify_2.crudJadwal.JadwalSaya
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.button.MaterialButton

class Tips : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<TipsCard> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        setFullScreen(window)
        lightStatusBar(window)

        findViewById<MaterialButton>(R.id.artikel1).setOnClickListener {
            val intent = Intent(this, Tips2::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.artikel2).setOnClickListener {
            val intent = Intent(this, Tips2::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.artikel3).setOnClickListener {
            val intent = Intent(this, Tips2::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.artikel4).setOnClickListener {
            val intent = Intent(this, Tips2::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.artikel5).setOnClickListener {
            val intent = Intent(this, Tips2::class.java)
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, HalamanUtama::class.java)
            startActivity(intent)
        }
        findViewById<BottomNavigationItemView>(R.id.itemHome).setOnClickListener {
            val intent = Intent(this, HalamanUtama::class.java)
            startActivity(intent)
        }
        findViewById<BottomNavigationItemView>(R.id.itemJadwal).setOnClickListener {
            val intent = Intent(this, JadwalSaya::class.java)
            startActivity(intent)
        }
        findViewById<BottomNavigationItemView>(R.id.itemPengingat).setOnClickListener {
            val intent = Intent(this, PengingatSaya::class.java)
            startActivity(intent)
        }
        findViewById<BottomNavigationItemView>(R.id.itemTips).setOnClickListener {
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