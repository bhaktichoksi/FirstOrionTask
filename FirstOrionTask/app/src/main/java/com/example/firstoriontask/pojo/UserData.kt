package com.example

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserData: Serializable {
    @SerializedName("userId")
    @Expose
    var userId: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null

    constructor() {}

    constructor(userId: Int?, id: Int?, title: String?, body: String?) : super() {
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }

}