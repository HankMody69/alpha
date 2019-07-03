package com.example.alpha.ui.wallet.add

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.alpha.R
import kotlinx.android.synthetic.main.activity_addwallet.*

class AddWalletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addwallet)
        initUiComponents()
        setupFragment()
    }

    private fun initUiComponents() {
        setSupportActionBar(toolbar_addwallet)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_addwallet.setNavigationOnClickListener { finish() }
        progressbar_addwallet.indeterminateDrawable
            .setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN)
    }

    private fun setupFragment() {
        val fragment = AddWalletFragment.newInstance()
        AddWalletPresenter(fragment)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_addwallet_container, fragment).commit()
    }

}
