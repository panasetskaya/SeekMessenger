package com.panasetskaia.seekmessenger.utils

import java.util.regex.Pattern

fun validateEmail(emailStr: String): Boolean {
    val emailRegex: Pattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    val matcher = emailRegex.matcher(emailStr)
    return matcher.find()
}

fun validatePassword(password: String): Boolean {
    val passwordRegex: Pattern =
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    val matcher = passwordRegex.matcher(password)
    return matcher.find()
}