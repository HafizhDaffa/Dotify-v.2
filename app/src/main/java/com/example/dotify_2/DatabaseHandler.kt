package com.example.dotify_2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.w3c.dom.Text
import java.lang.Exception
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

val DATABASE_NAME = "MyDB"
val DATABASE_VERSION = 1
val TABLE_NAME = "pengingat"
val COL_JUDUL = "judul"
val COL_TANGGAL = "tanggal"
val COL_JAM = "jam"

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

class DatabaseHandler(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_JUDUL + " VARCHAR(256), " +
                COL_TANGGAL + " TEXT" + ");" +
                COL_JAM + " TEXT" + ");"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }



    private fun getDateTimeCalendar(){
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }

    fun insertData(pengingat: Pengingat): Long {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_JUDUL, pengingat.judul)
        cv.put(COL_TANGGAL, pengingat.tanggal)
        cv.put(COL_JAM, pengingat.jam)


        var result = db.insert(TABLE_NAME, null, cv)
        db.close()
        return result
    }

//     @SuppressLint("Range")
//    fun getAllPengingat(): ArrayList<Pengingat>{
//        val pengingatList : ArrayList<Pengingat> = ArrayList()
//        val selectQuery = "SELECT * FROM $TABLE_NAME"
//        val db = this.readableDatabase
//
//        val cursor : Cursor?
//
//        try {
//            cursor = db.rawQuery(selectQuery, null)
//        }
//        catch (e: Exception){
//            e.printStackTrace()
//            db.execSQL(selectQuery)
//            return ArrayList()
//        }
//        var judul : String
//        var tanggal : String
//        var jam : String
//
//        if(cursor.moveToFirst()){
//            do {
//                judul = cursor.getString(cursor.getColumnIndex("judul"))
//                tanggal = cursor.getString(cursor.getColumnIndex("tanggal"))
//                jam = cursor.getString(cursor.getColumnIndex("jam"))
//                val pengingat = Pengingat ()
//                pengingatList.add(pengingat)
//            } while (cursor.moveToNext())
//        }
//         return pengingatList
//    }

}




