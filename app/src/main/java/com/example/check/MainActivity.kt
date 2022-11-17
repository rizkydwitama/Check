package com.example.check

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val SIGN_IN_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener{(mulaiLogin())}

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.authState.observe(this, Observer { updateUI(it) })
    }

    private fun mulaiLogin() {
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        startActivityForResult(intent, SIGN_IN_REQUEST_CODE)
    }

    private fun updateUI(user: FirebaseUser?){
//        login.text = if (user == null)
//            getString(R.string.Login)
//        else
//            getString(R.string.Logout)

        if (user == null) {
            namaTextView.visibility = View.GONE
            imageView.visibility = View.GONE
            login.text = getString(R.string.login)
        } else {
            namaTextView.text = user.displayName
            Glide.with(this).load(user.photoUrl).dontAnimate().into(imageView)

            namaTextView.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
            login.text = getString(R.string.logout)
        }
    }

    private fun mulailogin() {
        if(login.text == getString(R.string.logout)) {
            AuthUI.getInstance().signOut(this)
            return
        }
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == SIGN_IN_REQUEST_CODE) {
//            val response = IdpResponse.fromResultIntent(data)
//            if (resultCode == Activity.RESULT_OK) {
//                val nama = FirebaseAuth.getInstance().currentUser.displayName
//                Log.i("LOGIN", "$nama berhasil login")
//            } else {
//                Log.i("LOGIN", "Login gagal: ${response}")
//            }
//        }
//    }
}