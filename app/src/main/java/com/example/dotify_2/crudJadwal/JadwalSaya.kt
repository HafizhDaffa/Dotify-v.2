package com.example.dotify_2.crudJadwal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotify_2.*
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class JadwalSaya : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<Jadwal> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        val dbJadwal = DatabaseHandlerJadwal(this)
        val cursor = dbJadwal.getJadwal()
        val intent: Intent
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_saya)
        setFullScreen(window)
        lightStatusBar(window)

        if (cursor.count == 0) {
            Toast.makeText(this, "Tidak ada jadwal", Toast.LENGTH_LONG).show()
        }

        while(cursor.moveToNext()){
            list.add(Jadwal(cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(0)))//ati2, abis nambah cursor.getString(4)
            Log.i("cursor", cursor.getString(1))
        }

        findViewById<MaterialButton>(R.id.tambah_jadwal).setOnClickListener {
            val intent = Intent(this, TambahJadwal::class.java)
            Log.d("list", list.toString())
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
        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, HalamanUtama::class.java)
            startActivity(intent)
        }

//        list.addAll(CeritanyaDatabase.listJadwal)
//        list.sortWith(compareBy({it.tanggal.reversed()}, {it.jam}))
//        for (i in list.indices) {
//            if (i < list.size-1) {
//                if (list[i].tanggal == list[i+1].tanggal)
//                    list[i+1].isFirst = false
//            }
//        }

        Log.i("list", list.size.toString())

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterJadwal(list, this)
        recyclerView.adapter = adapter
    }
}