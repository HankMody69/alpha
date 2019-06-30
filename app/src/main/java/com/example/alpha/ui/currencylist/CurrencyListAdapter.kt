package com.example.alpha.ui.currencylist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alpha.R
import com.example.alpha.data.network.model.Currency
import kotlinx.android.synthetic.main.row_currency.view.*

class CurrencyListAdapter(private val data: List<Currency>, private val f: (position: Int) -> Unit) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    //private val data = mutableListOf<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_currency, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = data[position]
        holder.itemView.textview_currency_info.text = "${currency.name} - ${currency.symbol} - ${currency.code}"
    }

    /*fun addAll(data: List<Currency>) {
        Log.i("INFO", "data size is: ${data.size}")
        this.data.addAll(data)
        notifyDataSetChanged()
    }*/

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { f(adapterPosition) }
        }
    }

}