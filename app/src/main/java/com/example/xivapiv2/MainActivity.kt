package com.example.xivapiv2


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton


class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create()

        val loginButton = findViewById<LoginButton>(R.id.login_button)

        loginButton.setOnClickListener{
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))
        }

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("FACEBOOK_LOGIN", "Success Login")
                //get user info
            }

            override fun onCancel() {
                Log.d("FACEBOOK_LOGIN", "Canceled Login")
            }

            override fun onError(error: FacebookException?) {
                Log.d("FACEBOOK_LOGIN", "Failed Login")
            }
            
        })




    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}