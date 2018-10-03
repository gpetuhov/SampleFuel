package com.gpetuhov.android.samplefuel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.moshi.responseObject
import com.github.kittinunf.result.Result
import com.gpetuhov.android.samplefuel.model.QuakeResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnClickListener { loadData() }
        textView.setOnLongClickListener {
            loadQuake()
            true
        }
    }

    private fun loadData() {
        // The simplest way to load data from the network.
        // This is the async request.
        "http://google.com".httpGet().responseString { request, response, result ->
            val (data, error) = result
            if (error == null) {
                textView.text = data
            } else {
                textView.text = error.message
            }
        }
    }

    private fun loadQuake() {
        // This is how we can add params to GET request.
        // Result is deserialized automatically by Moshi.
        Fuel.get("https://earthquake.usgs.gov/fdsnws/event/1/query", listOf("format" to "geojson", "limit" to "10"))
                .responseObject<QuakeResult> { request, response, result ->
                    val (quakeResult, error) = result
                    if (error == null) {
                        textView.text = quakeResult?.quakeList?.firstOrNull()?.quakeProperties?.location
                    } else {
                        textView.text = error.message
                    }
                }
    }
}
