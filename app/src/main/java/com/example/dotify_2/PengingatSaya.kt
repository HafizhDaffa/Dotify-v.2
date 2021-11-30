package com.example.dotify_2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotify_2.crudJadwal.JadwalSaya
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.button.MaterialButton

class PengingatSaya : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<Pengingat> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        val myDB = DatabaseHandler(this)
        val cursor = myDB.getPengingat()
        val intent: Intent
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengingat_saya)
        setFullScreen(window)
        lightStatusBar(window)

        if (cursor.count == 0) {
            Toast.makeText(this, "Tidak ada pengingat", Toast.LENGTH_LONG).show()
            /*var intent: Intent = Intent(this, TambahPengingat::class.java)
            startActivity(intent)*/
        }

        while(cursor.moveToNext()){
            list.add(Pengingat(cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getInt(0)))
            Log.i("cursor", cursor.getString(1))
        }
        findViewById<MaterialButton>(R.id.tambah_pengingat).setOnClickListener {
            val intent = Intent(this, TambahPengingat::class.java)
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





//        list.sortWith(compareBy({ it.tgl?.reversed() }, {it.jam}))
//        for (i in list.indices) {
//            if (i < list.size-1) {
//                if (list[i].tgl == list[i+1].tgl){}
////                    list[i+1].isFirst = false
//            }
//        }

        Log.i("list", list.size.toString())

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterPengingat(list, this)
        recyclerView.adapter = adapter
    }
}