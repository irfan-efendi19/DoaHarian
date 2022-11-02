package com.sekolahbetasurabaya.doaharian

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userEmail: String
    private lateinit var auth: FirebaseAuth

    val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listdoa = findViewById<RecyclerView>(R.id.tempatdoa)
        listdoa.adapter = adapterDoa()


        val cvDoa = findViewById<RecyclerView>(R.id.tempatdoa)
//        cvDoa.adapter = adapterDoa().apply {
//            setNewData(dataDoa)
//        }


        GlobalScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
            val resdata = retrofitService().getDoa()
            if (resdata.isSuccessful) {
                val newDoaData = resdata.body()
                cvDoa.adapter = adapterDoa().apply {
                    setNewData(newDoaData.orEmpty())
                }
            }
        }

        //FIREBASE

        auth = Firebase.auth

        userEmail = getEmailPre()

        userEmail = intent.getStringExtra("user") ?: "tidak dikenal"

        findViewById<TextView>(R.id.cek).apply {
            text = userEmail
        }

        findViewById<Button>(R.id.buttonlogout).apply {
            setOnClickListener {
                logout()
            }
        }

    }

    private fun getEmailPre(): String {
        val sharedPref = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        return sharedPref.getString("email", "Tidak Dikenal")!!
    }

    private fun logout() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun retrofitService(): apiservice {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://doa-doa-api-ahmadramadhan.fly.dev/")
            .build()
            .create(apiservice::class.java)
    }
}