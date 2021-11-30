package com.example.dotify_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dotify_2.crudJadwal.JadwalSaya
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.button.MaterialButton

class HalamanUtama: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_utama)

        setFullScreen(window)
        lightStatusBar(window)

        findViewById<Button>(R.id.jadwal_saya).setOnClickListener {
            var intent: Intent = Intent(this, JadwalSaya::class.java)
            startActivity(intent)
        }

        findViewById<MaterialButton>(R.id.pengingat_saya).setOnClickListener {
            val intent = Intent(this, PengingatSaya::class.java)
            startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.TipsDanTrik).setOnClickListener {
            val intent = Intent(this, Tips::class.java)
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
    }
}