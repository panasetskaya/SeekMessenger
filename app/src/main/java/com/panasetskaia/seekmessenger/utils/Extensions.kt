package com.panasetskaia.seekmessenger.utils

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.panasetskaia.seekmessenger.R
import java.util.regex.Pattern

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






fun validateEmail(emailStr: String): Boolean {
    val emailRegex: Pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    val matcher = emailRegex.matcher(emailStr)
    return matcher.find()
}

fun validatePassword(password: String): Boolean {
    val passwordRegex: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    val matcher = passwordRegex.matcher(password)
    return matcher.find()
}