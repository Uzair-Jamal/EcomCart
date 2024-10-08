package com.example.ecomcart.fragments.shopping

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.ecomcart.R
import com.example.ecomcart.activities.ShoppingActivity
import com.example.ecomcart.adapters.ColorsAdapter
import com.example.ecomcart.adapters.SizesAdapter
import com.example.ecomcart.adapters.ViewPager2Images
import com.example.ecomcart.databinding.FragmentProductDetailsBinding
import com.example.ecomcart.util.hideBottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductDetailsFragment: Fragment() {
    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2Images()}
    private val sizesAdapter by lazy { SizesAdapter()}
    private val colorsAdapter by lazy { ColorsAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        hideBottomNavigationView()

        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        setupSizesRv()
        setupColorsRv()
        setupViewPagerRv()

        binding.crossImage.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.apply {
            tvProductName.text = product.name
            tvProductPrice.text = "$ ${product.price}"
            tvProductDescription.text = product.description
        }

        if(product.colors.isNullOrEmpty())
            binding.tvProductColor.visibility = View.INVISIBLE

        if(product.sizes.isNullOrEmpty())
            binding.tvProductSize.visibility = View.INVISIBLE

        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let{colorsAdapter.differ.submitList(it)}
        product.sizes?.let{sizesAdapter.differ.submitList(it)}
    }

    private fun setupViewPagerRv() {
        binding.apply {
            viewPagerProductImages.adapter = viewPagerAdapter
        }
    }

    private fun setupColorsRv() {
        binding.apply {
            productColorRv.adapter = colorsAdapter
            productColorRv.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL,false)
        }
    }

    private fun setupSizesRv() {
        binding.apply{
            productSizeRv.adapter = sizesAdapter
            productSizeRv.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        }
    }
}