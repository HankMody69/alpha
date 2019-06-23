package com.example.alpha.ui.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alpha.R
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.alpha.data.network.model.Currency
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter
import kotlinx.android.synthetic.main.fragment_exchange.*


class ExchangeFragment : Fragment(), ExchangeContract.View {

    override lateinit var mPresenter: ExchangeContract.Presenter
    private val baseTextWatcher by lazy { BaseTextWatcher() }
    private val convertTextWatcher by lazy { ConvertTextWatcher() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiListeners()
        mPresenter.getCurrencyList()
        mPresenter.getCurrencyRateList()
    }

    private fun initUiListeners() {
        edittext_exchange_base.addTextChangedListener(baseTextWatcher)
        edittext_exchange_converted.addTextChangedListener(convertTextWatcher)
    }

    override fun showProgress() {
    }

    override fun dismissProgress() {
    }

    override fun updateList(data: List<String>) {
        val adapter = ArrayAdapter<String>(context!!, com.jaredrummler.materialspinner.R.layout.ms__list_item, data)
        spinner_exchange_currencies.adapter = adapter
    }

    override fun updateRate(number: Double) {
        if (edittext_exchange_base.isFocused) {
            edittext_exchange_converted.setText(number.toString())
        } else if (edittext_exchange_converted.isFocused) {
            edittext_exchange_base.setText(number.toString())
        }
    }

    companion object {

        fun newInstance() = ExchangeFragment()

    }

    inner class BaseTextWatcher : TextWatcher {

        override fun afterTextChanged(p0: Editable?) {
            if (!edittext_exchange_base.text.isNullOrBlank()) {
                val number = edittext_exchange_base?.text?.toString()?.trim()?.toDouble()
                val spinner = spinner_exchange_currencies.selectedItemPosition
                number?.let { mPresenter.calculateRate(it, spinner) }
            } else {
                edittext_exchange_converted.setText("")
            }
            edittext_exchange_converted.addTextChangedListener(convertTextWatcher)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            edittext_exchange_converted.removeTextChangedListener(convertTextWatcher)
        }

    }

    inner class ConvertTextWatcher : TextWatcher {

        override fun afterTextChanged(p0: Editable?) {
            if (!edittext_exchange_converted.text.isNullOrBlank()) {
                val number = edittext_exchange_converted?.text?.toString()?.trim()?.toDouble()
                val spinner = spinner_exchange_currencies.selectedItemPosition
                number?.let { mPresenter.calculateToBase(it, spinner) }
            } else {
                edittext_exchange_base.setText("")
            }
            edittext_exchange_base.addTextChangedListener(baseTextWatcher)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            edittext_exchange_base.removeTextChangedListener(baseTextWatcher)
        }

    }

}