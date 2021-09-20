package com.example.firstoriontask.fragment

import android.R.attr.fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firstoriontask.Api
import com.example.firstoriontask.MainActivity
import com.example.firstoriontask.R
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CreatePost : Fragment() {

    var btnPost: Button? = null
    var edtTitle: EditText? = null
    var edtBody: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.create_post_fragment, container, false)

        btnPost = view.findViewById(R.id.btnPost);
        edtTitle = view.findViewById(R.id.edtTitle)
        edtBody = view.findViewById(R.id.edtBody)

        edtTitle?.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View, hasFocus: Boolean) {
                if (!hasFocus) {
                    hideSoftKeyboard(v)
                }
            }
        })
        edtBody?.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View, hasFocus: Boolean) {
                if (!hasFocus) {
                    hideSoftKeyboard(v)
                }
            }
        })

        btnPost?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if (edtTitle?.text!!.isEmpty()) {
                    edtTitle?.setError("Please enter title text")
                } else if (edtBody?.text!!.isEmpty()) {
                    edtBody?.setError("Please enter body text")
                } else {
                    CreatePost()
                }

            }
        })

        return view
    }

    fun hideSoftKeyboard(v: View) {
        val inputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

    private fun CreatePost() {
        val ROOT_URL = "https://jsonplaceholder.typicode.com"
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getApiService(): Api {
            return getRetrofitInstance().create(Api::class.java)
        }

        val api = getApiService()
        val jsonobject = JsonObject()
        jsonobject.addProperty("title", edtTitle?.text.toString())
        jsonobject.addProperty("body", edtBody?.text.toString())
        jsonobject.addProperty("userId", 101)

        val call = api.sendUsersData(jsonobject)
        println("Jsonobject" + jsonobject)

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {

                if (response != null) {
                    if (response.isSuccessful) {
                        val jobject = JSONObject(response.body().toString())
                        println("response" + jobject)

                        Toast.makeText(
                            context,
                            "Post Successfully Inserted.",
                            Toast.LENGTH_SHORT
                        ).show()
                        activity?.onBackPressed()
                        startActivity(Intent(context, MainActivity::class.java))

                    }
                }
            }
        });
    }
}