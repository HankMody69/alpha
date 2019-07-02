package com.example.alpha.ui.wallet.list

import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alpha.R
import com.example.alpha.data.database.sqlite.DatabaseContract
import kotlinx.android.synthetic.main.fragment_walletlist.*

class WalletListFragment : Fragment(), WalletListContract.View {

    override lateinit var mPresenter: WalletListContract.Presenter
    private lateinit var listAdapter: WalletListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_walletlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        lisIsEmpty()
        recyclerview_walletlist_wallets.layoutManager = LinearLayoutManager(context)
        listAdapter = WalletListAdapter()
        recyclerview_walletlist_wallets.adapter = listAdapter
        mPresenter.getWallets(context!!)
    }

    fun updateList(vararg wallet: String ) {
        listAdapter.addAll(*wallet)
        listIsNotEmpty()
    }

    override fun addWallet(wallet: String) {
        updateList(wallet)
    }

    override fun lisIsEmpty() {
        linearlayout_walletlist_empty.visibility = View.VISIBLE
    }

    override fun listIsNotEmpty() {
        linearlayout_walletlist_empty.visibility = View.GONE
    }

    companion object {

        fun newInstance() = WalletListFragment()

    }

}