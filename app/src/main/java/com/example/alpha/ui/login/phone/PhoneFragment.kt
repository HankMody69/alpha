package com.example.alpha.ui.login.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alpha.R
import kotlinx.android.synthetic.main.fragment_phone.*

class PhoneFragment : Fragment(), PhoneContract.View {

    override lateinit var mPresenter: PhoneContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_phone_send.setOnClickListener { mPresenter.login("09127201487", "123") }
    }

    override fun setMessage(message: String) {

    }

    companion object {

        fun newInstance() = PhoneFragment()

    }

}