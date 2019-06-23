package com.example.alpha.ui.login.phone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alpha.R
import com.example.alpha.ui.login.code.CodeActivity
import com.example.alpha.util.Utils
import com.example.alpha.util.snack
import kotlinx.android.synthetic.main.fragment_phone.*

class PhoneFragment : Fragment(), PhoneContract.View {

    override lateinit var mPresenter: PhoneContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiListeners()
    }

    private fun initUiListeners() {
        button_phone_send.setOnClickListener {
            if (Utils.isConnectedToInternet(context!!)) {
                Utils.hideKeyboard(this.activity!!)
                if (validatePhone()) {
                    Log.i("PhoneInfo", getFUllPhoneNumber())
                    //TODO [change this part later
                    mPresenter.login(getFUllPhoneNumber())
                } else {
                    setMessage("شماره همراه وارد شده صحیح نمی‌باشد.")
                }
            } else {
                setMessage("دسترسی به اینترنت موجود نمی‌باشد.")
            }

        }
    }

    override fun setMessage(message: String) {
        linearlayout_phone_root.snack(message) {}
    }

    override fun enableSend() {
        button_phone_send.isEnabled = true
    }

    override fun disableSend() {
        button_phone_send.isEnabled = false
    }

    override fun openNextActivity(bundle: Bundle) {
        Handler().postDelayed({
            val launchIntent = Intent(context, CodeActivity::class.java)
            launchIntent.putExtra("data", bundle)
            startActivity(launchIntent)
            activity?.finish()
        }, 2500)
    }

    private fun getFUllPhoneNumber() =
        edittext_phone_countrycode.text.toString() + edittext_phone_phonenumber.text.toString()

    private fun validatePhone(): Boolean {
        return edittext_phone_countrycode.text.toString().isNotBlank() && edittext_phone_phonenumber.text.isNotBlank()
    }

    companion object {

        fun newInstance() = PhoneFragment()

    }

}