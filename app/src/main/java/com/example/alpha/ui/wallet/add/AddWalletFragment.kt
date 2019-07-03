package com.example.alpha.ui.wallet.add

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.alpha.R
import com.example.alpha.util.snack
import kotlinx.android.synthetic.main.fragment_addwallet.*
import org.jetbrains.annotations.NonNls

class AddWalletFragment : Fragment(), AddWalletContract.View {

    override lateinit var mPresenter: AddWalletContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_addwallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiListeners()
        mPresenter.getCurrencies()
    }

    override fun initList(data: List<String>) {
        val adapter = ArrayAdapter<String>(context!!, com.jaredrummler.materialspinner.R.layout.ms__list_item, data)
        spinner_addwallet_currencies.adapter = adapter
    }

    override fun showMessage(message: String) {
        nestedscrollview_addwallet_root.snack(message) {}
    }

    private fun initUiListeners() {
        materialbutton_addwallet_save.setOnClickListener {
            val name = edittext_addwallet_name.text.toString()
            val currency = spinner_addwallet_currencies.selectedItem as String
            mPresenter.addWallet(context!!, name, currency)
        }
    }

    override fun finishWithResult(result: Int, data: String) {
        val intent = Intent()
        intent.putExtra("data", data)
        activity?.setResult(result, intent)
        activity?.finish()
    }

    override fun dismissProgress() {
        activity?.findViewById<ProgressBar>(R.id.progressbar_addwallet)?.visibility = View.GONE
    }

    override fun showProgress() {
        activity?.findViewById<ProgressBar>(R.id.progressbar_addwallet)?.visibility = View.VISIBLE
    }

    companion object {

        fun newInstance() = AddWalletFragment()

    }

}