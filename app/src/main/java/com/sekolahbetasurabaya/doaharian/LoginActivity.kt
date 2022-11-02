package com.sekolahbetasurabaya.doaharian

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val buttonSignin = findViewById<Button>(R.id.buttonsignin)
        val register = findViewById<Button>(R.id.btregister)
        val atEmail = findViewById<EditText>(R.id.editTextTextEmailAddress3)
        val atPassword = findViewById<EditText>(R.id.editTextTextPassword3)

        register.setOnClickListener{
            Register(atEmail.text.toString(),atPassword.text.toString())
        }

        buttonSignin.setOnClickListener{
            signinFirebase(atEmail.text.toString(),atPassword.text.toString())
        }
    }

    private fun Register(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    startActivity(
                        Intent(this,MainActivity::class.java)
                            .putExtra("user",user)
                    )
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext,"GAGAL : ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun signinFirebase(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    storeEmail(user!!.email!!)
                    startActivity(
                        Intent(this,MainActivity::class.java).putExtra("user",user)
                    )
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Periksa Kembali Email dan Sandi Anda",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun storeEmail(email:String){
        val sharedPref = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        sharedPref.edit().putString("email",email).apply()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this,MainActivity::class.java).putExtra("user",currentUser.email));
            finish()
        }
    }
}