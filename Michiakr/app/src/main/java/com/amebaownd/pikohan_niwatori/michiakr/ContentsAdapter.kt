package com.amebaownd.pikohan_niwatori.michiakr

import android.app.ActionBar
import android.content.Context
import android.support.v4.view.MarginLayoutParamsCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import java.lang.Math.round
import java.text.SimpleDateFormat
import java.util.*

class ContentsAdapter(
    private val context: Context,
    private var contents:ArrayList<Contents>,
    private val inNewSort:Int) : RecyclerView.Adapter<ContentsAdapter.ContentsViewHolder>(){
    val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ContentsViewHolder {
        val view = inflater.inflate(R.layout.contents_list_row,parent,false)
        return ContentsViewHolder(view)
    }

    override fun getItemCount(): Int = contents.size

    override fun onBindViewHolder(viewHolder: ContentsViewHolder, position: Int) {
        viewHolder.title.text = contents[position].title
        viewHolder.name.text = contents[position].userName
        val aaa = Date()
        viewHolder.uploadTime.text =((Date().time - contents[position].date.time)/3600/24/1000).toString()+"日前"
        viewHolder.like.text=contents[position].like.toString()
        setTags(contents[position].tags,viewHolder.tagLayout)
    }

    class ContentsViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.title)
        val name = view.findViewById<TextView>(R.id.name)
        val uploadTime = view.findViewById<TextView>(R.id.upload_time)
        val tagLayout = view.findViewById<LinearLayout>(R.id.tag_layout)
        val like = view.findViewById<TextView>(R.id.like)
    }
    private fun setTags(tags:List<String>,parent: LinearLayout){
        for(i in 0 until tags.size){
            val tagTextView = TextView(context)
            tagTextView.text = tags[i]
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0,0,10,0)
            tagTextView.layoutParams=layoutParams
            tagTextView.setPadding(10,2,10,2)
            tagTextView.setBackgroundResource(R.drawable.tag_frame)
            parent.addView(tagTextView)
        }
    }

}