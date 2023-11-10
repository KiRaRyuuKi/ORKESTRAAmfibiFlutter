package com.orkestra.paycars.models.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.AdapterDataMessage

class MainMessageActivity : AppCompatActivity() {

    private val url = "https://alquran-93bec.web.app/doa/listdoa.json"
    var recyclerView: RecyclerView? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var adapterData: AdapterDataMessage? = null
    var listData: MutableList<DataModelMessage>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_message)
        recyclerView = findViewById(R.id.rvDate)
        listData = ArrayList()
        getData()
    }

    private fun getData() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val stringRequest =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                val datamodel = DataModelMessage()
                listData = ArrayList()
                try {
                    val jsonObject = JSONObject(response)
                    val jsonArray: JSONArray = jsonObject.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val dataModel = DataModelMessage()
                        val data = jsonArray.getJSONObject(i)
                        dataModel!!.judul = data.getString("Judul")
                        dataModel!!.gambar = data.getString("img")
                        listData!!.add(dataModel!!)
                    }
                    linearLayoutManager = LinearLayoutManager(
                        this@MainMessageActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    recyclerView!!.layoutManager = linearLayoutManager
                    adapterData = AdapterDataMessage(this@MainMessageActivity, listData!!)
                    recyclerView!!.adapter = adapterData
                    adapterData!!.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(
                    this@MainMessageActivity,
                    "Error: " + error.message,
                    Toast.LENGTH_SHORT
                ).show()
            })

        requestQueue.add(stringRequest)
    }
}
