package com.komeyama.view_pager_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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
            tab.setCustomView(R.layout.custom_tab)
            val tabViewText = tab.view.findViewById<TextView>(R.id.tab_text)
            tabViewText.text =
                ("${resources.getString(R.string.tab_name)} ${position + 1}").toString()

            val tabViewImage = tab.view.findViewById<ImageView>(R.id.tab_image)
            when (position) {
                0 -> tabViewImage.setImageResource(R.drawable.ic_baseline_create_24)
                1 -> tabViewImage.setImageResource(R.drawable.ic_baseline_email_24)
                2 -> tabViewImage.setImageResource(R.drawable.ic_baseline_date_range_24)
            }
        }.attach()

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                for (tabID in 0..tabList.size) {
                    if (tabID == position) continue
                    setTabTextColor(tabID, R.color.white)
                }
                setTabTextColor(position, R.color.purple_200)
            }
        })
    }

    private fun setTabTextColor(position: Int, colorID: Int) {
        binding.tabLayout.getTabAt(position)?.customView?.findViewById<TextView>(R.id.tab_text)
            ?.setTextColor(ContextCompat.getColor(applicationContext, colorID))
    }

    private inner class PagerAdapter(f: FragmentActivity) : FragmentStateAdapter(f) {
        override fun getItemCount(): Int = tabList.size

        override fun createFragment(position: Int): Fragment = PagerFragment.create(position)
    }
}