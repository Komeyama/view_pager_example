package com.komeyama.view_pager_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.komeyama.view_pager_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val tabList: List<String> = listOf("tab1", "tab2", "tab3")
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
    }

    override fun onStart() {
        super.onStart()
        val pagerAdapter = PagerAdapter(this)
        binding.pager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabList[position]
        }.attach()

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {})
    }

    private inner class PagerAdapter(f: FragmentActivity) : FragmentStateAdapter(f) {
        override fun getItemCount(): Int = tabList.size

        override fun createFragment(position: Int): Fragment = PagerFragment.create(position)
    }
}