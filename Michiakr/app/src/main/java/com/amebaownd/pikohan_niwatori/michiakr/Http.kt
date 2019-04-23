package com.amebaownd.pikohan_niwatori.michiakr

import org.json.JSONObject
import java.io.BufferedInputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

fun httpGet(url:String):BufferedInputStream?{
    val connect = URL(url).openConnection() as HttpsURLConnection
    connect.apply {
        requestMethod="GET"
        connectTimeout=10000
        readTimeout = 10000
        instanceFollowRedirects = true
    }
    connect.connect()

    if(connect.responseCode in 200..299){
        return BufferedInputStream(connect.inputStream)
    }
    return null
}