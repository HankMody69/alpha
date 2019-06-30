package com.example.alpha.ui.currencylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alpha.R
import java.util.*

class CurrencyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configLayoutDirection()
        setContentView(R.layout.activity_currencylist)
        attachFragment()
    }

    private fun configLayoutDirection() {
        resources.configuration.setLayoutDirection(Locale("fa", "IR"))
    }

    private fun attachFragment() {
        val fragment = CurrencyListFragment.newInstance()
        CurrencyListPresenter(fragment)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_currencylist_container, fragment).commit()
    }

}
