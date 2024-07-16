package com.example.ecomcart.util

import android.util.Patterns

fun validateEmail(email:String):RegisterValidation{
    if(email.isEmpty())
        return RegisterValidation.Failed("Email can not be Empty")

    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong Email format")

    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation{
    if(password.isEmpty())
        return RegisterValidation.Failed("Password can not be Empty")

    if(password.length<6)
        return RegisterValidation.Failed("Wrong Password format")

    return RegisterValidation.Success
}