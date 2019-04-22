package com.amebaownd.pikohan_niwatori.michiakr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TestFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.test_fragment,container,false)
        val text = view.findViewById<TextView>(R.id.fragment_text)
        text.text = "Success"
        text.textAlignment= View.TEXT_ALIGNMENT_CENTER
        return view
    }
}