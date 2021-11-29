package com.example.dotify_2.crudJadwal

data class Jadwal(
    var judul: String? = null,
    var tanggal: String?=null,
    var catatan: String? = null,
    var jam: String? = null,
    var id: Int? = null
)
//{
//    var isFirst: Boolean
//    var tanggal = ""
//    var jam = ""
//    init {
//        isFirst = true
//        tanggal = date.date.toString().padStart(2, '0') + "/" + date.month.toString().padStart(2, '0') + "/" + date.year.toString()
//        jam = date.hours.toString() + "." + date.minutes.toString()
//    }
//}
