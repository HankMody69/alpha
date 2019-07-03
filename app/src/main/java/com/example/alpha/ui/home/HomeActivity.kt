package com.example.alpha.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.alpha.R
import com.example.alpha.ui.currencylist.CurrencyListActivity
import com.example.alpha.ui.exchange.ExchangeActivity
import com.example.alpha.ui.wallet.list.WalletListActivity
import com.example.alpha.util.Utils
import okhttp3.internal.Util
import java.util.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mPresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resources.configuration.setLayoutDirection(Locale("fa", "IR"))
        setContentView(R.layout.activity_home)
        Log.i("SHA1 Info", Utils.getPhoneUdid(this))
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        val fragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_home_container, fragment)
            .commit()
        mPresenter = HomePresenter(this, fragment)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_currencies -> {
                startActivity(Intent(this, CurrencyListActivity::class.java))
            }
            R.id.nav_exchange -> {
                startActivity(Intent(this, ExchangeActivity::class.java))
            }
            R.id.nav_wallets -> {
                startActivity(Intent(this, WalletListActivity::class.java))
            }
            R.id.item_home_logout -> {
                mPresenter.logout(this)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
