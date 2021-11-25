package com.example.dotify_2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.time.Year
import java.util.*
import kotlinx.android.synthetic.main.activity_tambah_pengingat.*
import java.lang.Exception
import java.text.SimpleDateFormat


class TambahPengingat: AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tambah_pengingat)


        setFullScreen(window)
        lightStatusBar(window)

        pickDate()
        val context = this

        findViewById<MaterialButton>(R.id.simpan).setOnClickListener {
            var judul = findViewById<EditText>(R.id.NamaTugas)
            var tanggal = findViewById<EditText>(R.id.btn_timePicker)
            var jam = findViewById<EditText>(R.id.btn_timePicker2)
            val databaseHandler = DatabaseHandler(this)
//            databaseHandler.insertData(Pengingat())
            val intent = Intent(this, PengingatSaya::class.java)
            startActivity(intent)

        }



        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, PengingatSaya::class.java)
            startActivity(intent)
        }
    }


    private fun getDateTimeCalendar(){
        val cal : Calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
        dateFormat.format(cal)
    }

    private fun pickDate(){
        findViewById<MaterialButton>(R.id.btn_timePicker).setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this, this, year, month, day).show()
        }
    }
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this, this, hour, minute, true).show()

    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        findViewById<MaterialButton>(R.id.btn_timePicker).text = "$savedDay-$savedMonth-$savedYear"
        findViewById<MaterialButton>(R.id.btn_timePicker2).text = "$savedHour:$savedMinute"

    }

}