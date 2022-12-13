package com.panasetskaia.seekmessenger.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.panasetskaia.seekmessenger.databinding.FragmentChatListBinding


class ChatListFragment : Fragment() {

    private var _binding: FragmentChatListBinding? = null
    private val binding: FragmentChatListBinding
        get() = _binding ?: throw RuntimeException("FragmentChatListBinding is null")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}