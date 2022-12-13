package com.panasetskaia.seekmessenger.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.firebase.auth.FirebaseAuth
import com.panasetskaia.seekmessenger.R
import com.panasetskaia.seekmessenger.databinding.FragmentTabSignUpBinding
import com.panasetskaia.seekmessenger.utils.validateEmail
import com.panasetskaia.seekmessenger.utils.validatePassword


class TabSignUpFragment : Fragment() {

    lateinit var auth: FirebaseAuth

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
        auth = FirebaseAuth.getInstance()
        setupButtonListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtonListener() {
        binding.signupButton.setOnClickListener {
            val email = binding.etEmail.text?.toString()
            val password = binding.etPassword.text?.toString()
            if (validateInputEmail(email) && validateInputPassword(password)) {
                (activity as MainActivity).auth.createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase", "createUserWithEmail:success")
                            replaceWithFragment(ChatListFragment::class.java)
                        } else {
                            Log.w("Firebase", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, getString(R.string.auth_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else if (!validateInputEmail(email)) {
                Toast.makeText(
                    context, getString(R.string.no_such_email),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context, getString(R.string.invalid_password),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateInputEmail(email: String?): Boolean {
        return if (email != null) {
            validateEmail(email)
        } else false
    }

    private fun validateInputPassword(password: String?): Boolean {
        return if (password != null) {
            validatePassword(password)
        } else false
    }

    private fun replaceWithFragment(fragment: Class<out Fragment>) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.mainFragmentContainer, fragment, null)
            addToBackStack(fragment.toString())
        }
    }

}