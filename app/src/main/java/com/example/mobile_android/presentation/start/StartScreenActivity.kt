package com.example.mobile_android.presentation.start

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_android.R
import com.google.firebase.auth.FirebaseAuth

class StartScreenActivity : AppCompatActivity() {

    override fun onBackPressed() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Toast.makeText(this, "You cannot return!", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.start_screen_container, StartScreenFragment.newInstance()).commit()
        }
    }
}
