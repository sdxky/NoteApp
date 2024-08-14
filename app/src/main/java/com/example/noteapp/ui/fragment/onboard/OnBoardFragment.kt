package com.example.noteapp.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapter.OnBoardAdapter


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        dotsChange()

    }


    private fun initialize() {
        binding.viewpager2.adapter = OnBoardAdapter(this)
    }


    private fun setupListeners() = with(binding.viewpager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.txtSkip.visibility = View.INVISIBLE
                } else {
                    binding.txtSkip.visibility = View.VISIBLE
                }
            }
        })
        binding.txtSkip.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 2, true)
            }
        }
    }


    private fun dotsChange() = with(binding.viewpager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.tabLayout1.setBackgroundResource(R.drawable.selected_dot)
                } else {
                    binding.tabLayout1.setBackgroundResource(R.drawable.default_dot)
                }
                if (position == 1) {
                    binding.tabLayout2.setBackgroundResource(R.drawable.selected_dot)
                } else {
                    binding.tabLayout2.setBackgroundResource(R.drawable.default_dot)
                }
                if (position == 2) {
                    binding.tabLayout3.setBackgroundResource(R.drawable.selected_dot)
                } else {
                    binding.tabLayout3.setBackgroundResource(R.drawable.default_dot)
                }
            }
        })
    }

}