package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.content.AsyncTaskLoader
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Contents(
    val title: String,
    val userName: String,
    val date: Date,
    val tags: ArrayList<String>,
    val like: Int
)

fun parseJSON(json: String): ArrayList<Contents> {
    val slicedJson = json.substring(1,json.length-2)
    val obj =JSONObject(slicedJson)
    val contents = obj.getJSONArray("data")
    val result = ArrayList<Contents>()
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.JAPAN)
    for(i in 0 until contents.length()){
        val content = contents.getJSONObject(i)
        val tags =content.getJSONArray("tags")
        val tagArrayList= arrayListOf<String>()
        for(j in 0 until tags.length()){
            tagArrayList.add(tags.getJSONObject(j).getString("name"))
        }
        result.add(Contents(
            title=content.getString("title"),
            userName = content.getJSONObject("user").getString("name"),
            date =dateFormatter.parse(content.getJSONObject("user").getString("created_at")),
            tags = tagArrayList,
            like = content.getString("likes_count").toInt()
        ))
    }
    return result
}

class ContentsLoader(context: Context) : AsyncTaskLoader<ArrayList<Contents>>(context) {
    override fun loadInBackground(): ArrayList<Contents>? {
        val jsonString = httpGet("https://michiakr.com/api/articles")

        if (jsonString != null) {
            return parseJSON(jsonString)
        }
        return null
    }

    override fun deliverResult(data: ArrayList<Contents>?) {
        super.deliverResult(data)
    }

    override fun onStartLoading() {
        super.forceLoad()
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()
        onStopLoading()
    }
}
