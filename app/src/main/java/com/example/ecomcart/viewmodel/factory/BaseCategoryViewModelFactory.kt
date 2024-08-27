package com.example.ecomcart.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecomcart.data.Category
import com.example.ecomcart.viewmodel.CategoryViewModel
import com.google.firebase.firestore.FirebaseFirestore

class BaseCategoryViewModelFactory(
    private val category: Category,
    private val firebaseFirestore: FirebaseFirestore
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(category = category, firestore = firebaseFirestore) as T
    }
}