package com.example.fetchrewards.viewholder


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.firstoriontask.R

class UserDetailsFragment : Fragment() {
    var txtTitle: TextView? = null
    var txtBody: TextView? = null
    var txtUserid: TextView? = null

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_user_details, container, false)
        txtTitle = view.findViewById(R.id.tvTitle)
        txtBody = view.findViewById(R.id.tvBody)
        txtUserid = view.findViewById(R.id.tvUserId)
        return view
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arguments = arguments

        if (arguments != null) {
            val title = arguments["TITLE"].toString()
            val body = arguments["BODY"].toString()
            val userid = arguments["USERID"].toString()
            txtTitle!!.text = title
            txtBody!!.text = body
            txtUserid!!.text = userid
        }
    }

}