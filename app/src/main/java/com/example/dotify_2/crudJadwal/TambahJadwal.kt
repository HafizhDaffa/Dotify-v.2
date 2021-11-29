package com.example.dotify_2.crudJadwal

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.dotify_2.R
import com.example.dotify_2.lightStatusBar
import com.example.dotify_2.setFullScreen
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_tambah_jadwal.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class TambahJadwal : AppCompatActivity() {
    var cal = Calendar.getInstance()
    lateinit var judul: TextView
    lateinit var catatan: TextView
    var intMDay: Int = 0
    var intMMonth: Int = 0
    var intMYear: Int = 0
    var intMHour: Int = 0
    var intMMinutes: Int = 0


    var tanggal: String = ""
    var jam: String = ""
    val dbJadwal = DatabaseHandlerJadwal(this)

    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_jadwal)
        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val hour = cal.get(Calendar.HOUR)
        val minute = cal.get(Calendar.MINUTE)
        setFullScreen(window)
        lightStatusBar(window)

        findViewById<MaterialButton>(R.id.btn_timePicker).setOnClickListener {
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

        findViewById<MaterialButton>(R.id.btn_timePicker2).setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                btn_timePicker2.text = SimpleDateFormat("HH:mm").format(cal.time)

                var strMHour = hour.toString()
                var strMMinutes = minute.toString()

                if (hour < 10) {
                    strMHour = "0" + strMHour
                }

                if (minute < 10) {
                    strMMinutes = "0" + strMMinutes
                }

                jam = "$strMHour:$strMMinutes"
            }


            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        findViewById<MaterialButton>(R.id.btn_timePicker3).setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                btn_timePicker3.text = SimpleDateFormat("HH:mm").format(cal.time)

                var strMHour = hour.toString()
                var strMMinutes = minute.toString()

                if (hour < 10) {
                    strMHour = "0" + strMHour
                }

                if (minute < 10) {
                    strMMinutes = "0" + strMMinutes
                }

                jam = "$strMHour:$strMMinutes"
            }


            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        judul = findViewById<EditText>(R.id.NamaTugas)
        catatan = findViewById<EditText>(R.id.Catatan)

        findViewById<MaterialButton>(R.id.simpan).setOnClickListener {
            if (checkAllField()) {

                dbJadwal.insertJadwal(
                    Jadwal(
                        judul.text.toString(),
                        tanggal,
                        catatan.text.toString(),
                        jam,
                    )
                )

                val intent = Intent(this, JadwalSaya::class.java)
                startActivity(intent)
            }
        }

        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, JadwalSaya::class.java)
            startActivity(intent)
        }
    }
    fun checkAllField(): Boolean {

        if ((TextUtils.isEmpty(judul.text)) or (TextUtils.isEmpty(tanggal) or (TextUtils.isEmpty(jam)))) {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_LONG).show()
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