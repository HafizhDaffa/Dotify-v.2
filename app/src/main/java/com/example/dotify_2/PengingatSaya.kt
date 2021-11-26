package com.example.dotify_2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class PengingatSaya : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<Pengingat> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengingat_saya)

        setFullScreen(window)
        lightStatusBar(window)

        findViewById<MaterialButton>(R.id.tambah_pengingat).setOnClickListener {
            val intent = Intent(this, TambahPengingat::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, HalamanUtama::class.java)
            startActivity(intent)
        }

        list.addAll(CeritanyaDatabase.listPengingat)
        list.sortWith(compareBy({ it.tgl?.reversed() }, {it.jam}))
        for (i in list.indices) {
            if (i < list.size-1) {
                if (list[i].tgl == list[i+1].tgl){}
//                    list[i+1].isFirst = false
            }
        }

        Log.i("list", list.size.toString())

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterPengingat(list)
        recyclerView.adapter = adapter
    }
}