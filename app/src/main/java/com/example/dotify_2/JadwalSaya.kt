package com.example.dotify_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class JadwalSaya : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<Jadwal> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_saya)

        findViewById<MaterialButton>(R.id.tambah_jadwal).setOnClickListener {
            val intent = Intent(this, TambahJadwal::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, HalamanUtama::class.java)
            startActivity(intent)
        }

        list.addAll(CeritanyaDatabase.listJadwal)
        list.sortWith(compareBy({it.tanggal.reversed()}, {it.jam}))
        for (i in list.indices) {
            if (i < list.size-1) {
                if (list[i].tanggal == list[i+1].tanggal)
                    list[i+1].isFirst = false
            }
        }

        Log.i("list", list.size.toString())

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterJadwal(list)
        recyclerView.adapter = adapter
    }
}