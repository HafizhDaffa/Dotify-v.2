package com.example.dotify_2

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.*
import kotlinx.android.synthetic.main.activity_tambah_pengingat.*
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period

//, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
class TambahPengingat : AppCompatActivity() {
    var cal = Calendar.getInstance()
    lateinit var judul: TextView
    var intMDay: Int = 0
    var intMMonth: Int = 0
    var intMYear: Int = 0
    var intMHour: Int = 0
    var intMMinutes: Int = 0
    lateinit var alarmManager: AlarmManager

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
        val hour = cal.get(Calendar.HOUR)
        val minute = cal.get(Calendar.MINUTE)
        setFullScreen(window)
        lightStatusBar(window)

        createNotificationChannel()

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

        judul = findViewById<EditText>(R.id.NamaTugas)

        findViewById<MaterialButton>(R.id.simpan).setOnClickListener {
            if (checkAllField()) {

                myDB.insertPengingat(
                    Pengingat(
                        judul.text.toString(),
                        tanggal,
                        jam,

                        )
                )

                alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, AlarmReceiver::class.java)
                val date = LocalDate.parse(
                    tanggal,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
                )
                val time = SimpleDateFormat("HH:ss").parse(jam)

                val intentToOpen = Intent(this, PengingatSaya::class.java)
                val pendingIntent =
                    PendingIntent.getBroadcast(this, time.time.toInt(), intent, 0)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, time.time, pendingIntent)

                startActivity(intentToOpen)
            }
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

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "dotify"
            val description = "Yuk dikerjain tugasnya"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("dotify", name, importance)
            channel.description = description

            val notificationManager =
                getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTanggal(): Int {
        return Period.between(
            LocalDate.of(intMYear, intMMonth, intMMonth),
            LocalDate.now()
        ).years
    }


}