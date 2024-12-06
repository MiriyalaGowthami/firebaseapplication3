package com.example.firebaseapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserInfoAdapter(private val context: Context, private val users:MutableList<UserInfo>):
    RecyclerView.Adapter<UserInfoAdapter.UserInfoHolder>() {
    class UserInfoHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val txtTitle:TextView=itemView.findViewById(R.id.txt_title)
        val txtDesc:TextView=itemView.findViewById(R.id.txt_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoHolder {
        return UserInfoHolder(LayoutInflater.from(context).inflate(R.layout.layout_user_item,parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserInfoHolder, position: Int) {
         holder.txtTitle.text=users[position].title ?: "No Title"
          holder.txtDesc.text=users[position].description ?: "No Description"
    }
}