package com.mohamedwessam.aklbety

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_slogan.*

class Slogan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slogan)

        btn_sign_Up_slogan.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        btn_sign_in_slogan.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
        }

    }
}
