package com.amebaownd.pikohan_niwatori.michiakr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.app.LoaderManager
import android.content.Loader

import android.util.Log
import java.io.Serializable
import java.util.ArrayList

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<ArrayList<Contents>>,Serializable{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loaderManager.initLoader(1, null, this)

    }

    private fun setTabLayout(data:ArrayList<Contents>) {
        val adapter = TabAdapter(supportFragmentManager, this,data)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        val tab: TabLayout.Tab = tabLayout.getTabAt(0)!!
        tab.customView = adapter.getTabView(tabLayout, 0)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<ArrayList<Contents>> {
        val test = ContentsLoader(this)
        return test
    }

    override fun onLoadFinished(loader: Loader<ArrayList<Contents>>?, data: ArrayList<Contents>?) {
        if (data != null) {
            for (i in 0 until data.size) {
                Log.d("This is Log", data.get(i).toString())
            }
            setTabLayout(data)
        }
    }

    override fun onLoaderReset(loader: Loader<ArrayList<Contents>>?) {
    }
}
