package com.panasetskaia.seekmessenger.utils

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.panasetskaia.seekmessenger.R

fun FirebaseAuth.createUser(
    email: String,
    password: String,
    context: Activity,
    fragment: Class<out Fragment>,
    updateUI: (fragment: Class<out Fragment>) -> Unit
) {
    this.createUserWithEmailAndPassword(email, password).addOnCompleteListener(context) { task ->
        if (task.isSuccessful) {
            Log.d("Firebase", "createUserWithEmail:success")
            val user = this.currentUser
            updateUI(fragment)
        } else {
            Log.w("Firebase", "createUserWithEmail:failure", task.exception)
            Toast.makeText(
                context, context.getString(R.string.auth_failed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}

fun FirebaseAuth.signInUser(
    email: String,
    password: String,
    context: Activity,
    fragment: Class<out Fragment>,
    updateUI: (fragment: Class<out Fragment>) -> Unit
) {
    this.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(context) { task ->
            if (task.isSuccessful) {
                Log.d("Firebase", "signInWithEmail:success")
                val user = this.currentUser
                updateUI(fragment)
            } else {
                Log.w("Firebase", "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    context, context.getString(R.string.auth_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}