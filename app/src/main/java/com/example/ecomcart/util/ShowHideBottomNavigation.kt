package com.example.ecomcart.util

import androidx.fragment.app.Fragment
import com.example.ecomcart.activities.ShoppingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


fun Fragment.hideBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            com.example.ecomcart.R.id.bottomNavigationMenu
        )

    bottomNavigationView.visibility = android.view.View.GONE
}

fun Fragment.showBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            com.example.ecomcart.R.id.bottomNavigationMenu
        )

    bottomNavigationView.visibility = android.view.View.VISIBLE
}