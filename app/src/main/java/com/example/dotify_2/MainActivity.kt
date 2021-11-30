package com.example.dotify_2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.dotify_2.crudJadwal.JadwalSaya
import com.example.dotify_2.crudJadwal.TambahJadwal
import com.example.dotify_2.crudJadwal.UbahJadwal

class MainActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFullScreen(window)
        lightStatusBar(window)

        Handler().postDelayed({
            startActivity(Intent(this,HalamanUtama::class.java ))
        },SPLASH_TIME_OUT)

  /*      findViewById<Button>(R.id.jadwal_saya).setOnClickListener {
            var intent: Intent = Intent(this, JadwalSaya::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.tambah_jadwal).setOnClickListener {
            var intent: Intent = Intent(this, TambahJadwal::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.ubah_jadwal).setOnClickListener {
            var intent: Intent = Intent(this, UbahJadwal::class.java)
            startActivity(intent)
        }
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
        }*/



    }
}