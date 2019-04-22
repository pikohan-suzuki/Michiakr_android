package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

//class ContentsAdapter(private val context: Context, private val contents:ArrayList<Contents>,private val isNewSort :Boolean = true):BaseAdapter() {
//    val inflater = LayoutInflater.from(context)
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val sortedContents = sortContents(contents)
//        val view = inflater.inflate(R.layout.contents_list_row, parent, false)
//        val viewHolder = ContentsViewHolder(view)
//        val content = sortedContents[position]
//        viewHolder.title.text = content.title
//        viewHolder.name.text = content.userName
//        viewHolder.uploadTime.text = content.date.toString()
//        viewHolder.like.text = content.like.toString()
//        setTags(content.tags,viewHolder.tagLayout)
//        return view
//    }
//    override fun getItem(position: Int) =contents[position]
//
//    override fun getItemId(position: Int): Long =position.toLong()
//
//    override fun getCount(): Int =contents.size
//
//    class ContentsViewHolder(view:View){
//        val title = view.findViewById<TextView>(R.id.title)
//        val name = view.findViewById<TextView>(R.id.name)
//        val uploadTime = view.findViewById<TextView>(R.id.upload_time)
//        val tagLayout = view.findViewById<LinearLayout>(R.id.tag_layout)
//        val like = view.findViewById<TextView>(R.id.like)
//    }
//    private fun setTags(tags:List<String>,parent: LinearLayout){
//        for(i in 0 until tags.size){
//            val tagTextView = TextView(context)
//            tagTextView.text = tags[i]
//            parent.addView(tagTextView)
//        }
//    }
//
//    private fun sortContents(contents: ArrayList<Contents>):ArrayList<Contents>{
//        if(isNewSort) contents.sortBy { it.date }
//        else contents.sortBy { it.like }
//        return contents
//    }
//}
class ContentsAdapter(private val context: Context, private var contents:ArrayList<Contents>,private val isNewSort :Boolean = true) : RecyclerView.Adapter<ContentsAdapter.ContentsViewHolder>(){
    val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ContentsAdapter.ContentsViewHolder {
        sortContents()
        val view = inflater.inflate(R.layout.contents_list_row,parent,false)
        val viewHolder = ContentsViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int = contents.size

    override fun onBindViewHolder(viewHolder: ContentsAdapter.ContentsViewHolder, position: Int) {
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
        if(isNewSort) contents.sortBy { it.date }
        else contents.sortBy { it.like }
    }
}