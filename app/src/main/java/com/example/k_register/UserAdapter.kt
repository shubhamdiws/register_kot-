package com.example.k_register

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class UserAdapter(private val UserList: List<UserModel>):
    RecyclerView.Adapter<UserAdapter.myViewHolder>() {

  //  lateinit var imageURI: URI
    //private var UserList= emptyList<UserModel>()

    class myViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val imageView: ImageView = itemView.findViewById(R.id.image_66)
        val name1: TextView = itemview.findViewById(R.id.dis_name23)
        val email1: TextView = itemview.findViewById(R.id.dis_email56)
        val phone: TextView = itemview.findViewById(R.id.dis_phone53)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)

        return myViewHolder(itemview)

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val UserModel = UserList[position]
        Log.d(TAG, "onBindViewHolder: HELL"+UserModel)
        val imageUri: String = UserModel.imageurl.toString()
        Glide.with(holder.itemView).load(imageUri).into(holder.imageView);
        holder.name1.text=UserModel.name.toString()
        holder.phone.text=UserModel.pnum.toString()
        holder.email1.text=UserModel.email.toString()

    }

    override fun getItemCount(): Int {
        return UserList.size

    }
}


