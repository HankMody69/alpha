package com.example.alpha.ui.login.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alpha.R
import kotlinx.android.synthetic.main.activity_phone.*
import java.util.*

class PhoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resources.configuration.setLayoutDirection(Locale("fa", "IR"))
        setContentView(R.layout.activity_phone)
        setSupportActionBar(toolbar_phone)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val fragment = PhoneFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_phone_container, fragment).commit()
        PhonePresenter(this, fragment)
    }
}
