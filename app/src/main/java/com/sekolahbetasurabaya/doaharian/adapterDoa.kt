package com.sekolahbetasurabaya.doaharian

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class adapterDoa() :
    RecyclerView.Adapter<doaHolder>() {

    private val dataset = arrayListOf<DoaItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): doaHolder {
        return doaHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.holderdoa, parent, false)
        )
    }

    override fun onBindViewHolder(holder: doaHolder, position: Int) {
        holder.bindview(dataset[position])
        holder.itemView.setOnClickListener {
            val movieIntent = dataDoa(
                dataset[position].doa.toString(),
                dataset[position].ayat.toString(),
                dataset[position].latin.toString(),
                dataset[position].artinya.toString()
            )
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.DETAIL_DOA, movieIntent)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun setNewData(ListDoa: List<DoaItem>) {
        dataset.clear()
        dataset.addAll(ListDoa)
        this.notifyDataSetChanged()
    }
}