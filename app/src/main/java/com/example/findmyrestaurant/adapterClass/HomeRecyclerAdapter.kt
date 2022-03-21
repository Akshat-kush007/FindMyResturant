package com.example.findmyrestaurant.adapterClass

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findmyrestaurant.R
import com.example.findmyrestaurant.models.Food
import com.squareup.picasso.Picasso

class HomeRecyclerAdapter(val context: Context,val itemList:ArrayList<Food>) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodName: TextView = view.findViewById(R.id.txtFoodName)
        val foodPrice:TextView=view.findViewById(R.id.txtFoodPrice)
        val ratingIcon:TextView=view.findViewById(R.id.ratingIcon)
        val rating:TextView=view.findViewById(R.id.txtrating)
        val foodImage:ImageView=view.findViewById(R.id.imgFoodImage)
        val lItem:LinearLayout=view.findViewById(R.id.lItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_view_layout, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val foodItem=itemList[position]
        holder.foodName.text=foodItem.foodName
        holder.rating.text=foodItem.foodRating
        holder.foodPrice.text=foodItem.foodPrice


//        holder.image.setImageResource(bookItem.Image)

//  Using the picasso implementation to fetch images from URL:

        Picasso.get().load(foodItem.foodImage).error(R.drawable.splash_image).into(holder.foodImage)

    }

    override fun getItemCount(): Int {
        return  itemList.size
    }
}