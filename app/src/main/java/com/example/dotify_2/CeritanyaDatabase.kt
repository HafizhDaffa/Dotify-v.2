package com.example.dotify_2

import java.util.*

object CeritanyaDatabase {
    var listPengingat = arrayListOf<Pengingat>(
        Pengingat(Date(2021, 11, 20, 21, 30), "Tugas PSI"),
        Pengingat(Date(2021, 11, 20, 18, 10), "Tugas RPL"),
        Pengingat(Date(2021, 11, 25, 20, 20), "Tugas JST"),
        Pengingat(Date(2021, 11, 8, 21, 30), "Tugas PSI 2"),
        Pengingat(Date(2021, 11, 30, 18, 10), "Tugas RPL 2"),
        Pengingat(Date(2022, 1, 25, 20, 20), "Tugas JST 2")
    )
    var listTips = arrayListOf<TipsCard>(
        TipsCard( "7 Tips Menjaga kesehatan tubuh"),
        TipsCard( "7 Tips Menjaga kesehatan tubuh"),
        TipsCard( "7 Tips Menjaga kesehatan tubuh"),
        TipsCard( "7 Tips Menjaga kesehatan tubuh"),
    )
}