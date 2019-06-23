package com.example.alpha.ui.login.code

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alpha.R
import com.example.alpha.data.network.model.LoginResponse
import com.example.alpha.ui.home.HomeActivity
import com.example.alpha.util.Utils
import com.example.alpha.util.snack
import kotlinx.android.synthetic.main.fragment_code.*

class CodeFragment : Fragment(), CodeContract.View {

    override lateinit var mPresenter: CodeContract.Presenter
    private lateinit var data: LoginResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiListeners()
        data = arguments?.get("data") as LoginResponse
        Log.i("DataInfo", data.phone + " - " + data.udid)
    }

    private fun initUiListeners() {
        button_code_send.setOnClickListener {
            if (Utils.isConnectedToInternet(context!!)) {
                Utils.hideKeyboard(this.activity!!)
                val code = edittext_code_activationcode.text.toString()
                Log.i("Code Response", "info: $code - ${data.phone} - ${data.udid}")
                mPresenter.activateLogin(code, data.phone, data.udid)
            } else {
                setMessage("دسترسی به اینترنت موجود نمی‌باشد.")
            }
        }
    }

    override fun setMessage(message: String) {
        linearlayout_code_root.snack(message) {}
    }

    override fun enableSubmit() {
        button_code_send.isEnabled = true
    }

    override fun disableSubmit() {
        button_code_send.isEnabled = false
    }

    override fun done() {
        Handler().postDelayed({
            val launchIntent = Intent(context, HomeActivity::class.java)
            startActivity(launchIntent)
            activity?.finish()
        }, 2500)
    }

    companion object {

        fun newInstance(bundle: Bundle) = CodeFragment().apply { arguments = bundle }

    }

}