package com.sekolahbetasurabaya.doaharian

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class doaHolder(itemview: View):
    RecyclerView.ViewHolder(itemview) {
        private val tvTitle = itemview.findViewById<TextView>(R.id.namadoa)

        fun bindview(doa: DoaItem){
            tvTitle.text = doa.doa
            itemView.setOnClickListener{(doa)}
        }

    }