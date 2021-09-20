package com.example.fetchrewards.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.UserData
import com.example.fetchrewards.viewholder.viewholderUsers
import com.example.firstoriontask.R

class UsersListAdapter(var iList: ArrayList<UserData>) :
    RecyclerView.Adapter<viewholderUsers>() {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderUsers {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.users_item_list, parent,
            false
        )

        context = parent.context
        return viewholderUsers(v)
    }

    override fun getItemCount(): Int {
        return iList.size
    }

    override fun onBindViewHolder(holder: viewholderUsers, position: Int) {
        holder.bindItems(iList[position])

    }
}