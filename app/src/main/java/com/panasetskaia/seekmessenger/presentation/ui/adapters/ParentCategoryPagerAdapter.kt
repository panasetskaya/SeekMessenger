package com.panasetskaia.seekmessenger.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.panasetskaia.seekmessenger.presentation.ui.TabSignInFragment
import com.panasetskaia.seekmessenger.presentation.ui.TabSignUpFragment

class ParentCategoryPagerAdapter(
    parentFragment: Fragment
) : FragmentStateAdapter(parentFragment) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabSignInFragment()
            1 -> TabSignUpFragment()
            else -> TabSignInFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}