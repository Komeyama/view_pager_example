package com.komeyama.view_pager_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.komeyama.view_pager_example.databinding.FragmentPagerBinding

class PagerFragment : Fragment(R.layout.fragment_pager) {

    private var _binding: FragmentPagerBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAB_POSITION = "tab_position"
        fun create(tabID: Int) = PagerFragment().apply {
            arguments = Bundle(1).apply {
                putInt(TAB_POSITION, tabID)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabID = arguments?.getInt(TAB_POSITION) ?: -1
        binding.text.text = ("tab no: $tabID").toString()

        binding.highlightButton.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_200))
        }
    }

}