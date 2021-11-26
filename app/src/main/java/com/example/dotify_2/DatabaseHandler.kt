package com.example.dotify_2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

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
                COL_JUDUL + " TEXT, " +
                COL_TANGGAL + " TEXT, " +
                COL_JAM + " TEXT" + ");"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val drop_table_pengingat = "DROP TABLE IF EXISTS $TABLE_NAME;"
        db?.execSQL(drop_table_pengingat)
        onCreate(db)
    }

    fun insertPengingat(pengingat: Pengingat): Long {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_JUDUL, pengingat.judul)
        cv.put(COL_TANGGAL, pengingat.tgl)
        cv.put(COL_JAM, pengingat.jam)

        val result = db.insert(TABLE_NAME, null, cv)
        db.close()
        return result
    }

    fun getPengingat(): Cursor {
        val db = this.writableDatabase
        return db!!.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

}





