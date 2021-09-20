package com.example.firstoriontask


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.UserData
import com.example.fetchrewards.adapter.UsersListAdapter
import com.example.firstoriontask.fragment.CreatePost
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    internal var moAdapter: UsersListAdapter? = null
    lateinit var recyclerView: RecyclerView
    val manager = SessionManager()
    var myresults = listOf<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.rvUsersList)

        try {
            myresults = manager.getArrayOfObjectList(applicationContext, "usersList")

            if (myresults.isEmpty()) {
                getUsersDatafromAPI()
            } else {
                getUsersData()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val fragment: Fragment = CreatePost()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment).commit()
            findViewById<RelativeLayout>(R.id.mainLayout).visibility = View.GONE

            //startActivity(Intent(this@MainActivity, CreatePost::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, MainActivity::class.java))

    }


    private fun getUsersDatafromAPI() {
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
        val call = api.getUsersData()

        call?.enqueue(object : Callback<List<UserData>> {
            override fun onFailure(call: Call<List<UserData>>?, t: Throwable?) {
            }

            override fun onResponse(
                call: Call<List<UserData>>?,
                response: Response<List<UserData>>?
            ) {
                if (response != null) {
                    if (response.isSuccessful) {
                        Log.d(ContentValues.TAG, "JSON REWARDS" + response.body())

                        var myresults = response.body()
                        manager.saveArrayOfObjectList(applicationContext, myresults, "usersList")
                        getUsersData()
                    }
                }
            }
        })
    }

    fun getUsersData(): ArrayList<UserData> {
        var userList = ArrayList<UserData>()

        try {
            if (myresults.isNotEmpty()) {
                for (i in 0 until myresults.size) {
                    var resultList = myresults.get(i)

                    if (resultList != null) {

                        userList.add(
                            UserData(
                                resultList.userId,
                                resultList.id,
                                resultList.title,
                                resultList.body
                            )
                        )
                        Log.e(ContentValues.TAG, "Success" + userList)

                        moAdapter = UsersListAdapter(userList)
                        recyclerView.adapter = moAdapter
                        recyclerView.layoutManager = LinearLayoutManager(
                            applicationContext,
                            RecyclerView.VERTICAL,
                            false
                        )
                    }
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return userList
    }
}
