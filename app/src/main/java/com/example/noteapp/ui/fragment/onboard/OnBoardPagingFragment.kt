package com.example.noteapp.ui.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.Lottie
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagingBinding
import com.google.android.material.tabs.TabLayout


class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

    }

    private fun initialize() = with(binding){
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 -> {
                txtOnboard.text = "Очень удобный функционал"
                lottieView.setAnimation(R.raw.lottie1)
                txtStart.visibility = View.INVISIBLE

            }
            1 -> {
                txtOnboard.text = "Быстрый, качественный продукт"
                lottieView.setAnimation(R.raw.lottie2)
                txtStart.visibility = View.INVISIBLE
            }
            2 -> {
                txtOnboard.text = "Куча функций и интересных фишек"
                lottieView.setAnimation(R.raw.lottie3)
                txtStart.visibility = View.VISIBLE

            }
        }
    }

    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"

    }
}