package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class ContentsAdapter(
    private val context: Context,
    private var contents:ArrayList<Contents>,
    private val isNewSort:Int) : RecyclerView.Adapter<ContentsAdapter.ContentsViewHolder>(){
    val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ContentsAdapter.ContentsViewHolder {
        sortContents()
        val view = inflater.inflate(R.layout.contents_list_row,parent,false)
        val viewHolder = ContentsViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int = contents.size

    override fun onBindViewHolder(viewHolder: ContentsViewHolder, position: Int) {
        viewHolder.title.text = contents[position].title
        viewHolder.name.text = contents[position].userName
        viewHolder.uploadTime.text = contents[position].date.toString()
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
            parent.addView(tagTextView)
        }
    }
    private fun sortContents(){
        if(isNewSort==0) contents.sortBy { it.date }
        else contents.sortBy { it.like }
    }
}