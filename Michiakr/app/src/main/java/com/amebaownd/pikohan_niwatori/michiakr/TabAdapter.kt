package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

class TabAdapter(fm: FragmentManager, private val context: Context,private val data:ArrayList<Contents>) : FragmentStatePagerAdapter(fm) {

    private val inflater = LayoutInflater.from(context)
    private val title = arrayOf<String>("最新の記事", "人気の記事")
    override fun getItem(position: Int): Fragment? {
        val bundle = Bundle()
        bundle.putInt("isNewSort",position)
        bundle.putSerializable("contents",data)
        val fragment = ContentsFragment()
        fragment.arguments=bundle
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }

    override fun getCount() = title.size

    fun getTabView(tabLayout: TabLayout, position: Int): View {
        val view = inflater.inflate(R.layout.tab_item, tabLayout, false)
        val tab = view.findViewById<TextView>(R.id.tab_name)
        tab.text = getPageTitle(position)
        tab.textAlignment = View.TEXT_ALIGNMENT_CENTER
        return view
    }
}