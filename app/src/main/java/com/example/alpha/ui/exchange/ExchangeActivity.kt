package com.example.alpha.ui.exchange

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.alpha.R
import kotlinx.android.synthetic.main.activity_exchange.*

class ExchangeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)
        initUiComponents()
        initFragment()
    }

    private fun initUiComponents() {
        setSupportActionBar(toolbar_exchange)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_exchange.setNavigationOnClickListener { finish() }
        progressbar_exchange.indeterminateDrawable
            .setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
    }

    private fun initFragment() {
        val fragment = ExchangeFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_exchange_container, fragment).commit()
        ExchangePresenter(fragment)
    }

}
