package com.example.dotify_2

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class AdapterJadwal(val listJadwal: ArrayList<Jadwal>) : RecyclerView.Adapter<AdapterJadwal.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.jadwal, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val jadwal = listJadwal[position]

        holder.tanggal.text = jadwal.tanggal
        holder.judul.text = jadwal.judul
        holder.catatan.text = jadwal.catatan
        holder.jam.text = jadwal.jam

        holder.note.setOnClickListener {
            val context = holder.judul.context
            val intent = Intent(context, UbahJadwal::class.java)
            context.startActivity(intent)
        }

        if (!jadwal.isFirst) {
            holder.tanggal.visibility = View.GONE
            holder.top.visibility = View.GONE
        } else {
            holder.top.visibility = View.INVISIBLE
        }
        Log.i("size", listJadwal.size.toString())
        Log.i("adapter", position.toString())

    }

    override fun getItemCount(): Int {
        return listJadwal.size
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var tanggal: TextView = view.findViewById(R.id.tanggal)
        var judul: TextView = view.findViewById(R.id.judul)
        var catatan: TextView = view.findViewById(R.id.catatan)
        var jam: TextView = view.findViewById(R.id.jam)
        var card: CardView = view.findViewById(R.id.card)
        var top: TextView = view.findViewById(R.id.top)
        var note: ImageView = view.findViewById(R.id.note)
    }
}