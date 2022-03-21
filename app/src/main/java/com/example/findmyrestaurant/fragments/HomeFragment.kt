package com.example.findmyrestaurant.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.findmyrestaurant.R
import com.example.findmyrestaurant.adapterClass.HomeRecyclerAdapter
import com.example.findmyrestaurant.models.Food
import com.example.findmyrestaurant.util.ConnectionManager
import org.json.JSONException


class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerLayout:RecyclerView.LayoutManager
    lateinit var recyclerAdapter: HomeRecyclerAdapter

    val foodInfoList= arrayListOf<Food>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//  Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView=view.findViewById(R.id.recyclerHome)
        recyclerLayout=LinearLayoutManager(activity)
//        recyclerAdapter= HomeRecyclerAdapter(activity as Context)
//        recyclerView.layoutManager=recyclerLayout
//        recyclerView.adapter=recyclerAdapter


        val queue= Volley.newRequestQueue(activity as Context)

        val url="http://13.235.250.119/v2/restaurants/fetch_result/ "
        val connectionManager= ConnectionManager()
        if (connectionManager.checkConnectin(activity as Context)) {


//  Creating the json Object---------------------------
            val jsonObjectRequest =
                object : JsonObjectRequest(Method.GET, url, null, Response.Listener {
//            println("The response is : ${it}")
//            parsing the json data in booklist


//  Getting response from json object--------------------------
                    try{
//                        layout_progressBar.visibility=View.GONE
                        val it2=it.getJSONObject("data")
                        val success = it2.getBoolean("success")
                        if (success) {
                            val data = it2.getJSONArray("data")
                            for (i in 0 until data.length()) {
                                val foodJsonbject = data.getJSONObject(i)
                                val bookObject = Food(
                                    foodJsonbject.getString("id"),
                                    foodJsonbject.getString("name"),
                                    foodJsonbject.getString("rating"),
                                    foodJsonbject.getString("cost_for_one"),
                                    foodJsonbject.getString("image_url")
                                )
                                foodInfoList.add(bookObject)
                            }

                            recyclerAdapter= HomeRecyclerAdapter(activity as Context,foodInfoList)
                            recyclerView.layoutManager=recyclerLayout
                            recyclerView.adapter = recyclerAdapter

//

                        } else {
                            Toast.makeText(activity as Context, "Error in fetching....", Toast.LENGTH_SHORT).show()
                        }
                    }catch (e: JSONException){
                        Toast.makeText(activity as Context,"Somthing went wrong in fetching responce",
                            Toast.LENGTH_SHORT).show()
                    }




                }, Response.ErrorListener {

//                    Volly error handled here---------
                    Toast.makeText(activity as Context,"Volly Error Occured", Toast.LENGTH_SHORT).show()

                }
                ) {
                    override fun getHeaders(): MutableMap<String, String> {

                        val headers = HashMap<String, String>()
                        headers["Content-type"] = "application/java"
                        headers["token"] = "9bf534118365f1"
                        return headers

                    }
                }
            queue.add(jsonObjectRequest)
        }else{
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Intenet Not Connected")
            dialog.setPositiveButton("Open Settings") {text, listener->

//   Implicite Intent -----------------------------------------------
                val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()
            }
            dialog.setNegativeButton("Close"){text,listener->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }

        return view
    }


}