package com.example.dotify_2

import java.util.*

data class Pengingat(
    var date: Date,
    var judul: String = "",

    )
{
    var isFirst: Boolean
    var tanggal = ""
    var jam = ""
    init {
        isFirst = true
        tanggal = date.date.toString().padStart(2, '0') + "/" + date.month.toString().padStart(2, '0') + "/" + date.year.toString()
        jam = date.hours.toString() + "." + date.minutes.toString()
    }
}
