package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class ContentsAdapter(private val context: Context, private val contents:ArrayList<Contents>):BaseAdapter() {
    val inflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.contents_list_row, parent, false)
        val viewHolder = ContentsViewHolder(view)
        val content = contents[position]
        viewHolder.title.text = content.title
        viewHolder.name.text = content.userName
        viewHolder.uploadTime.text = content.date.toString()
        viewHolder.like.text = content.like.toString()
        setTags(content.tags,viewHolder.tagLayout)
        return view
    }
    override fun getItem(position: Int) =contents[position]

    override fun getItemId(position: Int): Long =position.toLong()

    override fun getCount(): Int =contents.size

    class ContentsViewHolder(view:View){
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
}