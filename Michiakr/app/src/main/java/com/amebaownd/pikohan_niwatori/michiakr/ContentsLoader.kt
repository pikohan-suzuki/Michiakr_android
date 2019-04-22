package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.content.AsyncTaskLoader
import android.util.Log

import org.w3c.dom.NodeList
import java.io.InputStream
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

data class Contents(
     val title: String,
     val userName: String,
     val date: Date,
     val tags: List<String>,
     val like: Int
)

fun parseFormat(response: InputStream): ArrayList<Contents> {
    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response)
    response.close()

    val xPath = XPathFactory.newInstance().newXPath()

    val articles = xPath.evaluate(
//        "/div[@class=\"content-card v-card v-card--flat v-sheet theme--light\"",
        "//item",
        doc,
        XPathConstants.NODESET
    ) as NodeList

    val result = arrayListOf<Contents>()
    for (i in 0 until articles.length) {
        val article = Contents(
            title = "aaa",
            userName = "bbb",
            date = Date(),
            tags = listOf("aaa", "bbb"),
            like = 12
        )
        result.add(article)
    }
    return result
}

class ContentsLoader(context: Context) : AsyncTaskLoader<ArrayList<Contents>>(context) {
    override fun loadInBackground(): ArrayList<Contents>? {
        val response = httpGet("https://www.sbbit.jp/rss/HotTopics.rss")

        if (response != null) {
            return parseFormat(response)
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
