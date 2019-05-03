package com.mohamedwessam.aklbety

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignIn : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btn_sign_up_from_sign_in.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        database = FirebaseDatabase.getInstance().reference.child("user")


       btn_sign_in.setOnClickListener {

           val postListener = object : ValueEventListener {
               override fun onDataChange(dataSnapshot: DataSnapshot) {}

               override fun onCancelled(databaseError: DatabaseError) {
                   // Getting Post failed, log a message
                   // ...
               }
           }

       }

    }
}
