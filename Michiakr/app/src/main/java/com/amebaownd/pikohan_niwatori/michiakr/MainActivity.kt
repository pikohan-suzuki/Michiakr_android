package com.amebaownd.pikohan_niwatori.michiakr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTabLayout()
    }

    private fun setTabLayout(){
        val adapter = TabAdapter(supportFragmentManager,this)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        val tab:TabLayout.Tab = tabLayout.getTabAt(0)!!
        tab.customView = adapter.getTabView(tabLayout,0)
    }
}
