package com.example.dotify_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFullScreen(window)
        lightStatusBar(window)
        findViewById<Button>(R.id.halaman_utama).setOnClickListener {
            var intent: Intent = Intent(this, HalamanUtama::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.pengingat_saya).setOnClickListener {
            var intent: Intent = Intent(this, PengingatSaya::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.tambah_pengingat).setOnClickListener {
            var intent: Intent = Intent(this, TambahPengingat::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.ubah_pengingat).setOnClickListener {
            var intent: Intent = Intent(this, UbahPengingat::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.TipsDanTrik).setOnClickListener {
            var intent: Intent = Intent(this, Tips::class.java)
            startActivity(intent)
        }
    }
}