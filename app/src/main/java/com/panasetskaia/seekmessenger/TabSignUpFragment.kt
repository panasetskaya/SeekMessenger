package com.panasetskaia.seekmessenger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.google.firebase.auth.FirebaseUser
import com.panasetskaia.seekmessenger.databinding.FragmentTabSignUpBinding
import com.panasetskaia.seekmessenger.utils.createUser


class TabSignUpFragment : Fragment() {

    private var _binding: FragmentTabSignUpBinding? = null
    private val binding: FragmentTabSignUpBinding
        get() = _binding ?: throw RuntimeException("FragmentTabSignUpBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupButton.setOnClickListener {
            val email = binding.etEmail.text?.toString()
            val password = binding.etPassword.text?.toString()
            if (validateInputEmail(email) && validateInputPassword(password)) {
                (activity as MainActivity).auth.createUser(
                    email!!,
                    password!!,
                    requireActivity(),
                    ChatListFragment::class.java,
                    ::replaceWithFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateInputEmail(email: String?): Boolean {
        return if (email != null) {
            email.length > 5
        } else false
        //todo: сделать валидацию
    }

    private fun validateInputPassword(password: String?): Boolean {
        return if (password != null) {
            password.length > 8
        } else false
        //todo: сделать валидацию
    }

    private fun replaceWithFragment(fragment: Class<out Fragment>) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.mainFragmentContainer, fragment, null)
            addToBackStack(fragment.toString())
        }
    }

}