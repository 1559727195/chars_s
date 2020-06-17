package com.massky.chars_s.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.massky.chars_s.R
import com.massky.chars_s.bean.Repo
import com.massky.chars_s.service.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class TraceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handler_main)


        //ActivityManager
        // Context.DROPBOX_SERVICE
        // getSystemService()

        // ActivityManagerService.java
        // DropBoxManagerService.java
        // SystemServer.java

        //ActivityManagerService.java->appNotResponding


        get_retrofit()


        get_map()
    }

    private fun get_map() {

        var map = HashMap<String, Int>()

        for ((key, value) in map) {
            println("$key: $value")
        }
    }

    private fun get_retrofit() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(GitHubService::class.java)
        val repos = service.listRepos()
        repos!!.enqueue(object : Callback<List<Repo?>?> {
            override fun onFailure(call: Call<List<Repo?>?>?, t: Throwable?) {}
            override fun onResponse(call: Call<List<Repo?>?>?, response: Response<List<Repo?>?>?) {
                TODO("Not yet implemented")
            }
        })

        // service!!.ex


        /*   Call<List<User>> repos = service.createUser(new User(1, "管满满", "28", "http://write.blog.csdn.net/postlist"));

        val retrofit1: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service1 = retrofit1.create(GitHubService::class.java)

        service1.enqueue()
        service1.execute()*/
    }

    fun inputDispatchingTimedOut(pid: Int, aboveSystem: Boolean, reason: String?): Long {
        return 0
    }


    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }


}