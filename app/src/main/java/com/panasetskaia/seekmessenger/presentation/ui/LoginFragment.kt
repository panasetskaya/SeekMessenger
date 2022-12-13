package com.panasetskaia.seekmessenger.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.panasetskaia.seekmessenger.R
import com.panasetskaia.seekmessenger.presentation.ui.adapters.ParentCategoryPagerAdapter
import com.panasetskaia.seekmessenger.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var categoryPagerAdapter: ParentCategoryPagerAdapter

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragmentPager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupFragmentPager() {
        categoryPagerAdapter = ParentCategoryPagerAdapter(this)
        binding.viewPagerLogin.adapter = categoryPagerAdapter
        val tabTitles = resources.getStringArray(R.array.login_tabs)
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPagerLogin
        ) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

}