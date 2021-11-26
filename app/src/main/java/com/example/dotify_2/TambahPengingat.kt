package com.example.dotify_2

import android.view.ViewGroup
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.time.Year
import java.util.*
import kotlinx.android.synthetic.main.activity_tambah_pengingat.*
import java.lang.Exception
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import kotlin.math.roundToInt

//, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
class TambahPengingat: AppCompatActivity(){
    var cal = Calendar.getInstance()
    lateinit var judul: TextView
    var intMDay: Int = 0
    var intMMonth: Int = 0
    var intMYear: Int = 0
    var intMHour: Int = 0
    var intMMinutes: Int = 0
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

    var tanggal: String = ""
    var jam: String = ""
    val myDB = DatabaseHandler(this)
    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tambah_pengingat)
        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        var hour = cal.get(Calendar.HOUR)
        var minute = cal.get(Calendar.MINUTE)
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
            jam = "$intMHour-$intMMinutes"
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()


        }

        judul = findViewById<EditText>(R.id.NamaTugas)

        findViewById<MaterialButton>(R.id.simpan).setOnClickListener {
            if (checkAllField()) {

                myDB.insertPengingat(
                    Pengingat(
                        judul.text.toString(),
                        tanggal,
                        jam
                    )
                )

                val intent = Intent(this, PengingatSaya::class.java)
                startActivity(intent)
            }


        }



        findViewById<ImageButton>(R.id.kembali).setOnClickListener {
            val intent = Intent(this, PengingatSaya::class.java)
            startActivity(intent)
        }


    }




//    private fun getDateTimeCalendar(){
//        val cal : Calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        day = cal.get(Calendar.DAY_OF_MONTH)
//        month = cal.get(Calendar.MONTH)
//        year = cal.get(Calendar.YEAR)
//        hour = cal.get(Calendar.HOUR)
//        minute = cal.get(Calendar.MINUTE)
//        dateFormat.format(cal)
//    }

//    private fun pickDate(){
//        findViewById<MaterialButton>(R.id.btn_timePicker).setOnClickListener {
//            getDateTimeCalendar()
//
//            DatePickerDialog(this, this, year, month, day).show()
//        }
//    }
//    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        savedDay = dayOfMonth
//        savedMonth = month
//        savedYear = year
//
//        getDateTimeCalendar()
//        TimePickerDialog(this, this, hour, minute, true).show()
//
//    }

//    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
//        savedHour = hourOfDay
//        savedMinute = minute
//
//        findViewById<MaterialButton>(R.id.btn_timePicker).text = "$savedDay-$savedMonth-$savedYear"
//        findViewById<MaterialButton>(R.id.btn_timePicker2).text = "$savedHour:$savedMinute"
//
//    }
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

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getJam(): Int {
//        return
//    }
}