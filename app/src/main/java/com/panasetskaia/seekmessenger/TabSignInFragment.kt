package com.panasetskaia.seekmessenger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.panasetskaia.seekmessenger.databinding.FragmentLoginBinding
import com.panasetskaia.seekmessenger.databinding.FragmentTabSignInBinding
import com.panasetskaia.seekmessenger.databinding.FragmentTabSignUpBinding


class TabSignInFragment : Fragment() {

    private var _binding: FragmentTabSignInBinding? = null
    private val binding: FragmentTabSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentTabSignInBinding is null")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}