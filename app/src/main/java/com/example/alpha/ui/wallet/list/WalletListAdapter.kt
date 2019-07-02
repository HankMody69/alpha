package com.example.alpha.ui.wallet.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alpha.R
import kotlinx.android.synthetic.main.row_wallet.view.*

class WalletListAdapter : RecyclerView.Adapter<WalletListAdapter.ViewHolder>() {

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_wallet, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textview_details.text = items[position]
    }

    fun addAll(vararg wallet: String) {
        val start = items.size
        val count = wallet.size
        items.addAll(wallet)
        notifyItemRangeInserted(start, count)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}