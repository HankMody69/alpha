package com.example.alpha.ui.wallet.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun setupFragment() {
        val fragment = AddWalletFragment.newInstance()
        AddWalletPresenter(fragment)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_addwallet_container, fragment).commit()
    }

}
