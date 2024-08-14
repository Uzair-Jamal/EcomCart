package com.example.ecomcart.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomcart.R
import com.example.ecomcart.adapters.BestProductAdapter
import com.example.ecomcart.databinding.FragmentBaseCategoryBinding

open class BaseCategoryFragment : Fragment(R.layout.fragment_base_category) {
    private lateinit var binding: FragmentBaseCategoryBinding
    private lateinit var offerAdapter: BestProductAdapter
    private lateinit var bestProductsAdapter: BestProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOfferRv()
        setupBestProductsRv()
    }

    private fun setupBestProductsRv() {
        bestProductsAdapter = BestProductAdapter()
        binding.rvBestProductsInBaseCategory.apply{
            layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
            adapter = bestProductsAdapter
        }
    }

    private fun setOfferRv() {
        offerAdapter = BestProductAdapter()
        binding.rvOffer.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = offerAdapter
        }
    }
}