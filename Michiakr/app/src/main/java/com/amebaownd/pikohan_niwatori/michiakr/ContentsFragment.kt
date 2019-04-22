package com.amebaownd.pikohan_niwatori.michiakr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ContentsFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.contents_fragment,container,false)
        val contentsList = view.findViewById<RecyclerView>(R.id.contents_list)
        contentsList.adapter = ContentsAdapter(MainActivity,savedInstanceState.get("contents"),true)
        return view
    }
}