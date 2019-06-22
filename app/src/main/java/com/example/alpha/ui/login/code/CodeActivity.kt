package com.example.alpha.ui.login.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alpha.R
import kotlinx.android.synthetic.main.activity_code.*

class CodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)
        setSupportActionBar(toolbar_code)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val bundle = intent.getBundleExtra("data")
        val fragment = CodeFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_code_container, fragment).commit()
        CodePresenter(this, fragment)
    }
}
