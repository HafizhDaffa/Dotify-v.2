package com.example.dotify_2.crudJadwal

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.LayoutInflater
import androidx.core.content.contentValuesOf
import com.example.dotify_2.R
import com.example.dotify_2.crudJadwal.Jadwal

val DATABASE_NAME = "dbJadwal"
val DATABASE_VERSION = 1
val TABLE_NAME = "jadwal"
val COL_JUDUL = "judul"
val COL_TANGGAL = "tanggal"
val COL_CATATAN = "catatan"
val COL_JAM = "jam"
val ID = "id"

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

class DatabaseHandlerJadwal(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_JUDUL + " TEXT, " +
                COL_TANGGAL + " TEXT, " +
                COL_CATATAN + " TEXT, " + //ati2
                COL_JAM + " TEXT" + ");"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val drop_table_pengingat = "DROP TABLE IF EXISTS $TABLE_NAME;"
        db?.execSQL(drop_table_pengingat)
        onCreate(db)
    }

    fun insertJadwal(jadwal: Jadwal): Long {//hati2
        val db = this.readableDatabase
        val cv = ContentValues()

        cv.put(COL_JUDUL, jadwal.judul)
        cv.put(COL_TANGGAL, jadwal.tanggal)
        cv.put(COL_CATATAN, jadwal.catatan)
        cv.put(COL_JAM, jadwal.jam)

        val result = db.insert(TABLE_NAME, null, cv)
        db.close()
        return result
    }

    fun getJadwal(): Cursor {
        val db = this.writableDatabase
        return db!!.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    //@SuppressLint("Range")
    fun updateJadwal(jadwal: Jadwal): Int{//nnti coba yg gapake parameter
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_JUDUL, jadwal.judul)
        cv.put(COL_TANGGAL, jadwal.tanggal)
        cv.put(COL_CATATAN, jadwal.catatan)
        cv.put(COL_JAM, jadwal.jam)

        val result = db.update(TABLE_NAME, cv, "id=" + jadwal.id, null)
        return result
//        val query = "SELECT * FROM "+ TABLE_NAME
//        val result = db.rawQuery(query, null)
//        if(result.moveToFirst()){
//            do{
//                var cv = ContentValues()
//                cv.put(COL_JUDUL,(result.getInt(result.getColumnIndex(COL_JUDUL))+1))
//                db.update(TABLE_NAME, cv, ID+"=? AND "+ COL_JUDUL+ "=?",
//                    arrayOf(result.getString(result.getColumnIndex(ID)),
//                            result.getString(result.getColumnIndex(COL_TANGGAL)),
//                             result.getString(result.getColumnIndex(COL_CATATAN)),
//                            result.getString(result.getColumnIndex(COL_JAM))))
//            }while (result.moveToNext())
//
//        }
//        result.close()
//        db.close()
    }

    fun deleteJadwal(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, ID + " = " + id, null)
    }

}





