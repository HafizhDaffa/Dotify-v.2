package com.example.dotify_2


import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView


class AdapterPengingat (val ListPengingat: ArrayList<Pengingat>, val context: Context) : RecyclerView.Adapter<AdapterPengingat.Holder>() {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val myDB = DatabaseHandler(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pengingat, parent, false)

        return Holder(view)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pengingat = ListPengingat[position]

        holder.tanggal.text = pengingat.tgl
        holder.judul.text = pengingat.judul
        holder.jam.text = pengingat.jam

        holder.note.setOnClickListener {
            val context = holder.judul.context
            val intent = Intent(context, UbahPengingat::class.java)
            intent.putExtra("id", pengingat.id)
            Log.i("id", pengingat.id.toString())
            context.startActivity(intent)
        }

        holder.trash.setOnClickListener{
            val db = DatabaseHandler(context)
            db.deletePengingat(pengingat.id!!)
            ListPengingat.removeAt(position)
            notifyDataSetChanged()
        }

        Log.i("size", ListPengingat.size.toString())
        Log.i("adapter", position.toString())

    }



    override fun getItemCount(): Int {
        return ListPengingat.size
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var tanggal: TextView = view.findViewById(R.id.tanggal)
        var judul: TextView = view.findViewById(R.id.judul)
        var jam: TextView = view.findViewById(R.id.jam)
        var note: ImageView = view.findViewById(R.id.note)
        var trash: ImageView = view.findViewById(R.id.trash)
    }
}