package com.example.fetchrewards.viewholder


import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.UserData
import com.example.firstoriontask.MainActivity
import com.example.firstoriontask.R


class viewholderUsers(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItems(foUsers: UserData) {

        val txtID = itemView.findViewById(R.id.tvUserId) as TextView
        val txtTitle = itemView.findViewById(R.id.tvTitle) as TextView
        val txtBody = itemView.findViewById(R.id.tvBody) as TextView

        txtID.setText(foUsers.userId.toString())
        txtTitle.setText(foUsers.title.toString())
        txtBody.setText(foUsers.body.toString())

        val context = itemView.getContext() as Activity

        itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

                val activity = v!!.context as MainActivity
                val userDetailsFragment = UserDetailsFragment()
                val bundle = Bundle()
                bundle.putString("TITLE", foUsers.title)
                bundle.putString("BODY", foUsers.body)
                bundle.putString("USERID", foUsers.userId.toString())
                userDetailsFragment.setArguments(bundle)

                activity.supportFragmentManager.beginTransaction().replace(android.R.id.content,userDetailsFragment).addToBackStack(null).commit()
                activity.findViewById<RelativeLayout>(R.id.mainLayout).visibility = View.GONE
            }
        })


    }
}