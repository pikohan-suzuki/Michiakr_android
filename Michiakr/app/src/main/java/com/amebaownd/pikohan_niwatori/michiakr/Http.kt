package com.amebaownd.pikohan_niwatori.michiakr

import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

fun httpGet(url:String):String?{
    val connect = URL(url).openConnection() as HttpsURLConnection
    connect.apply {
        requestMethod="GET"
        connectTimeout=10000
        readTimeout = 10000
        instanceFollowRedirects = true
    }
    connect.connect()

    if(connect.responseCode in 200..299){
        val stream = connect.inputStream
        val reader = BufferedReader(InputStreamReader(stream))
        val buffer = StringBuffer()
        var line:String?
        while(true){
            line=reader.readLine()
            if(line==null)
                break
            buffer.append(line)
        }
        return buffer.toString()
    }
    return null
}