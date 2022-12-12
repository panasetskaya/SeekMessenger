package com.panasetskaia.seekmessenger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            goToChats()
        } else {
            goToLogin()
        }
    }

    private fun goToLogin() {
        replaceWithFragment(LoginFragment::class.java)
    }

    private fun goToChats() {
        replaceWithFragment(ChatListFragment::class.java)
    }

    private fun replaceWithFragment(fragment: Class<out Fragment>) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.mainFragmentContainer,fragment, null)
            addToBackStack(fragment.toString())
        }
    }
}