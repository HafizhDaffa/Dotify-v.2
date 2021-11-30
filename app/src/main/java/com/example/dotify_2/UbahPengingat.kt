package com.example.dotify_2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.dotify_2.crudJadwal.JadwalSaya
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_tambah_pengingat.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class UbahPengingat:AppCompatActivity() {
    var cal = Calendar.getInstance()
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0
    lateinit var judul: TextView
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0
    var tanggal: String = ""
    var jam: String = ""
    var intMDay: Int = 0
    var intMMonth: Int = 0
    var intMYear: Int = 0
    var intMHour: Int = 0
    var intMMinutes: Int = 0
    val myDB = DatabaseHandler(this)
    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_pengingat)

        var dapatID = getIntent().getIntExtra("id",0)

        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val hour = cal.get(Calendar.HOUR)
        val minute = cal.get(Calendar.MINUTE)
        setFullScreen(window)
        lightStatusBar(window)

        findViewById<MaterialButton>(R.id.btn_timePicker).setOnClickListener{
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    var date = LocalDate.of(mYear, mMonth + 1, mDay).format(formatter)
                    btn_timePicker.text = date.toString()

                    var monthStr = (mMonth + 1).toString()
                    var dayStr = mDay.toString()

                    intMDay = mDay
                    intMMonth = mMonth
                    intMYear = mYear

                    if (mMonth < 10) {
                        monthStr = "0" + monthStr
                    }

                    if (mDay < 10) {
                        dayStr = "0" + dayStr
                    }

                    tanggal = "$mYear-$monthStr-$dayStr"
                },
                year,
                month,
                day
            )
            dpd.show()
        }



       findViewById<MaterialButton>(R.id.btn_timePicker2).setOnClickListener{
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                btn_timePicker2.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            intMHour = Calendar.HOUR_OF_DAY
            intMMinutes = Calendar.MINUTE

            jam = "$intMHour : $intMMinutes"
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()


        }

        judul = findViewById<EditText>(R.id.NamaTugas)


        findViewById<MaterialButton>(R.id.simpan).setOnClickListener {
            if (checkAllField()) {

                myDB.updatePengingat(
                    Pengingat(
                        judul.text.toString(),
                        tanggal,
                        jam,
                        dapatID
                    )
                )

                val intent = Intent(this, PengingatSaya::class.java)
                startActivity(intent)
            }

            val intent = Intent(this, PengingatSaya::class.java)
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
            val intent = Intent(this, PengingatSaya::class.java)
            startActivity(intent)
        }

    }
    fun checkAllField(): Boolean {

        if (TextUtils.isEmpty(judul.text)) {
            Toast.makeText(this, "Masukkan Judul", Toast.LENGTH_LONG).show()
            return false
        }

        if (TextUtils.isEmpty(tanggal)) {
            Toast.makeText(this, "Masukkan Tanggal", Toast.LENGTH_LONG).show()
            return false
        }
        if (TextUtils.isEmpty(jam)) {
            Toast.makeText(this, "Masukkan Jam", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTanggal(): Int {
        return Period.between(
            LocalDate.of(intMYear, intMMonth, intMMonth),
            LocalDate.now()
        ).years
    }

}