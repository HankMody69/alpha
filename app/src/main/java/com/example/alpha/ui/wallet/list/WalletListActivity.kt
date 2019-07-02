package com.example.alpha.ui.wallet.list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alpha.R
import com.example.alpha.ui.wallet.add.AddWalletActivity
import kotlinx.android.synthetic.main.activity_walletlist.*

class WalletListActivity : AppCompatActivity() {

    private lateinit var fragment: WalletListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walletlist)
        initUiListeners()
        setupFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            val wallet = data?.extras?.getString("data", null)
            if (wallet != null) {
                fragment.updateList(wallet)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initUiListeners() {
        floatingactionbutton_walletlist_add.setOnClickListener {
            startActivityForResult(Intent(this, AddWalletActivity::class.java), 1000)
        }
    }

    private fun setupFragment() {
        fragment = WalletListFragment.newInstance()
        WalletListPresenter(fragment)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_walletlist_container, fragment).commit()
    }

}
