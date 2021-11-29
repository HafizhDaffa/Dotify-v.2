package com.example.dotify_2.crudJadwal

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dotify_2.R

class AdapterJadwal(val listJadwal: ArrayList<Jadwal>, val context: Context) : RecyclerView.Adapter<AdapterJadwal.Holder>() {
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
            intent.putExtra("id", jadwal.id)
            Log.i("id", jadwal.id.toString())
            context.startActivity(intent)
        }
        holder.trash.setOnClickListener{
            val db = DatabaseHandlerJadwal(context)
            db.deleteJadwal(jadwal.id!!)
            listJadwal.removeAt(position)
            notifyDataSetChanged()
        }

//        if (!jadwal.isFirst) {
//            holder.tanggal.visibility = View.GONE
//            holder.top.visibility = View.GONE
//        } else {
//            holder.top.visibility = View.INVISIBLE
//        }
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
        var trash: ImageView = view.findViewById(R.id.trash)
        var note: ImageView = view.findViewById(R.id.note)
    }
}