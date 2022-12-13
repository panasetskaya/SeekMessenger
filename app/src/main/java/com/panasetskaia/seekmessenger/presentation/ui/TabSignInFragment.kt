package com.panasetskaia.seekmessenger.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import com.google.firebase.auth.FirebaseAuth
import com.panasetskaia.seekmessenger.R
import com.panasetskaia.seekmessenger.databinding.FragmentTabSignInBinding

class TabSignInFragment : Fragment() {

    lateinit var auth: FirebaseAuth

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val email = binding.etEmail.text?.toString()
            val password = binding.etPassword.text?.toString()
            if (email!=null && password!=null) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase", "signInWithEmail:success")
                            replaceWithFragment(ChatListFragment::class.java)
                        } else {
                            Log.w("Firebase", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, getString(R.string.auth_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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