package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
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
        val contents = changeToContents(data)
        val isNewSort = arguments?.getInt("isNewSort")
        if (contents != null && isNewSort != null) {
            val test = ArrayList<Contents>()
            test.add(Contents("aaa","aaa", Date(), listOf("aaa"),23))
            val aaa = ContentsAdapter(view.context, test, 0)
            contentsList.adapter =aaa
//            contentsList.adapter=ContentsAdapter(view.context,contents,isNewSort)
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
}