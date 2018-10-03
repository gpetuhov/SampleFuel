package com.gpetuhov.android.samplefuel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnClickListener { loadData() }
    }

    private fun loadData() {
        // The simplest way to load data from the network
        "http://google.com".httpGet().responseString { request, response, result ->
            val (data, error) = result
            if (error == null) {
                textView.text = data
            } else {
                textView.text = error.message
            }
        }
    }
}
