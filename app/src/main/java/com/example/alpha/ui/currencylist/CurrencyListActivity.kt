package com.example.alpha.ui.currencylist

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.alpha.R
import kotlinx.android.synthetic.main.activity_currencylist.*
import java.util.*

class CurrencyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configLayoutDirection()
        setContentView(R.layout.activity_currencylist)
        setSupportActionBar(toolbar_currencylist)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_currencylist.setNavigationOnClickListener { finish() }
        progressbar_currencylist.indeterminateDrawable
            .setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
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
