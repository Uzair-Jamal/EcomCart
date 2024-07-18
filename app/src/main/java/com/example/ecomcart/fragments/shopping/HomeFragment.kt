package com.example.ecomcart.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecomcart.R
import com.example.ecomcart.adapters.HomeViewPagerAdapter
import com.example.ecomcart.databinding.FragmentHomeBinding
import com.example.ecomcart.fragments.categories.AccessoryFragment
import com.example.ecomcart.fragments.categories.ChairFragment
import com.example.ecomcart.fragments.categories.CupboardFragment
import com.example.ecomcart.fragments.categories.FurnitureFragment
import com.example.ecomcart.fragments.categories.MainCategoryFragment
import com.example.ecomcart.fragments.categories.TableFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            FurnitureFragment(),
            TableFragment(),
            CupboardFragment(),
            AccessoryFragment()
        )

        val viewPagerAdapter2 = HomeViewPagerAdapter(categoriesFragments,childFragmentManager,lifecycle)
        binding.viewPager.adapter = viewPagerAdapter2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position){
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Table"
                3 -> tab.text = "CupBoard"
                4 -> tab.text = "Furniture"
                5 -> tab.text = "Accessory"
            }
        }.attach()
    }
}