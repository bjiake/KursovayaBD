package com.example.newbd2.Collections

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.newbd2.R

fun FragmentActivity.goToFragment(fragment: Fragment, addToBackStack: Boolean) {
    val transaction = supportFragmentManager.beginTransaction()
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.add(R.id.container, fragment).commit()
}