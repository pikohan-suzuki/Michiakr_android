package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.io.Serializable
import java.util.*

class ContentsFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.contents_fragment, container, false)
        val contentsList = view.findViewById<RecyclerView>(R.id.contents_list)
        val data = arguments?.getSerializable("contents") as ArrayList<Contents>
        val isNewSort = arguments?.getInt("isNewSort")
        val sortedContents = sortContents(isNewSort,data)
        val contents = changeToContents(data)
        if (contents != null) {
            contentsList.adapter =ContentsAdapter(view.context, contents,isNewSort!!)
            val layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            contentsList.layoutManager = layoutManager
        }
        return view
    }

    private fun changeToContents(data: ArrayList<Contents>): ArrayList<Contents>? {
        val result: ArrayList<Contents> = ArrayList<Contents>()
        for (i in 0 until data.size) {
            result.add(Contents(data[i].title,data[i].userName,data[i].date,data[i].tags,data[i].like))
        }
        return result
    }
    private fun sortContents(isNewSort:Int?,contents:ArrayList<Contents>):ArrayList<Contents>{
        if(isNewSort==0) contents.sortBy { it.date.time*-1}
        else contents.sortBy { it.like*-1 }
        return contents
    }
}