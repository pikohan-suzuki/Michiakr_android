package com.amebaownd.pikohan_niwatori.michiakr

import android.content.Context
import android.content.AsyncTaskLoader
import org.json.JSONObject
import java.io.InputStream
import kotlin.collections.ArrayList

data class Contents(
    val title: String,
    val userName: String,
    val date: String,
    val tags: ArrayList<String>,
    val like: Int
)

//fun parseFormat(response: InputStream): ArrayList<Contents> {
//    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response)
//    response.close()
//
//    val xPath = XPathFactory.newInstance().newXPath()
//
//    val articles = xPath.evaluate(
//        "/div[@class=\"content-card v-card v-card--flat v-sheet theme--light\"",
////        "//item",
//        doc,
//        XPathConstants.NODESET
//    ) as NodeList
//
//    val result = arrayListOf<Contents>()
//    for (i in 0 until articles.length) {
//        val article = Contents(
//            title = "aaa",
//            userName = "bbb",
//            date = Date(),
//            tags = listOf("aaa", "bbb"),
//            like = 12
//        )
//        result.add(article)
//    }
//    return result
//}

//fun parseFormat(url: String): ArrayList<Contents> {
//    val result = arrayListOf<Contents>()
//    val driver = HtmlUnitDriver(true)
//    driver.get(url)
//    driver.findElementsByClassName("content-card v-card v-card--flat v-sheet theme--light").forEach {
//        val tags = ArrayList<String>()
//        it.findElements(By.ByClassName("v-chip__content")).forEach{
//            tags.add(it.text)
//        }
//        val content = Contents(
//            title = it.findElement(By.className("headline")).text,
//            userName = it.findElement(By.className("v-list__tile__title")).text,
//            date = it.findElement(By.className("gray-color")).text,
//            tags = tags,
//            like = it.findElement(By.className("gray-color")).text.toInt()
//        )
//        result.add(content)
//    }
//    return result
//}
fun parseJSON(response: InputStream): ArrayList<Contents> {
    val json = response.read().toString()
    val obj =JSONObject(json)
    val contents = obj.getJSONArray("")
    val result = ArrayList<Contents>()
    for(contents in 0 until contents.length()){
        result.add(Contents(
            title="aaa",
            userName = "aaa",
            date = "aaa",
            tags = arrayListOf("aaa"),
            like = 1
        ))
    }
    return result
}

class ContentsLoader(context: Context) : AsyncTaskLoader<ArrayList<Contents>>(context) {
    override fun loadInBackground(): ArrayList<Contents>? {
        val response = httpGet("https://michiakr.com/")

        if (response != null) {
            return parseJSON(response)
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
