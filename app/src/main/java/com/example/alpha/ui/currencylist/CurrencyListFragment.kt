package com.example.alpha.ui.currencylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alpha.R
import com.example.alpha.data.network.model.Currency
import com.example.alpha.util.snack
import kotlinx.android.synthetic.main.fragment_currencylist.*

class CurrencyListFragment : Fragment(), CurrencyListContract.View {

    override lateinit var mPresenter: CurrencyListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencylist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initUiComponents()
        mPresenter.getCurrencies()
    }

    override fun showProgress() {
        progressbar_currencylist.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progressbar_currencylist.visibility = View.GONE
    }

    override fun updateList(currencies: List<Currency>) {
        //adapter.addAll(currencies)
        recyclerview_currencylist_currencies.layoutManager = LinearLayoutManager(context)
        recyclerview_currencylist_currencies.adapter = CurrencyListAdapter(currencies) { clickedPosition: Int ->
            mPresenter.saveCurrency(this@CurrencyListFragment.activity?.applicationContext!!, clickedPosition)
        }
    }

    override fun setMessage(message: String) {
        framelayout_currencylist_root.snack(message) {}
    }

    /*private fun initUiComponents() {
        with(recyclerview_currencylist_currencies) {
            layoutManager = LinearLayoutManager(context)
            this@CurrencyListFragment.adapter = CurrencyListAdapter()
            adapter = this.adapter
        }
    }*/

    companion object {
        fun newInstance() = CurrencyListFragment()
    }

}