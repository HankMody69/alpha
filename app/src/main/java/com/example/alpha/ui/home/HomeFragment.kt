package com.example.alpha.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.alpha.R
import com.example.alpha.data.preferences.UserPreferencesHelper
import com.example.alpha.data.preferences.model.User
import com.example.alpha.ui.login.phone.PhoneActivity
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var mPresenter: HomeContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.isUserLoggedIn()
    }

    override fun isNotLoggedIn() {
        val launchIntent = Intent(context, PhoneActivity::class.java)
        activity?.startActivity(launchIntent)
        activity?.finish()
    }

    override fun setPhoneNumber(phone: String) {
        val activity = activity as? HomeActivity
        val nav = activity?.findViewById<NavigationView>(R.id.nav_view)
        val linearLayout = nav?.getHeaderView(0) as? LinearLayout
        val textView = linearLayout?.getChildAt(0) as? TextView
        textView?.text = "+$phone"
    }

    companion object {

        fun newInstance() = HomeFragment()

    }

}