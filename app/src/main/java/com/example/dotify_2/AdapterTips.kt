package com.example.dotify_2

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdapterTips (val ListTips: ArrayList<TipsCard>) : RecyclerView.Adapter<AdapterTips.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.tips, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pengingat = CeritanyaDatabase.listTips[position]

        holder.judul.text = pengingat.judul
        Log.i("size", CeritanyaDatabase.listTips.size.toString())
        Log.i("adapter", position.toString())

    }

    override fun getItemCount(): Int {
        return CeritanyaDatabase.listTips.size
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var judul: TextView = view.findViewById(R.id.judul)
    }
}