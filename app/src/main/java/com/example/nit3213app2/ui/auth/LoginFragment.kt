package com.example.nit3213app2.ui.auth

import android.R
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.nit3213app2.util.LoginValidator
import com.example.nit3213app2.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Fragment responsible for handling the login screen
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val vm: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // --- Campus dropdown ---
        val campuses = listOf("footscray", "sydney", "br")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, campuses)
        binding.campusInput.setAdapter(adapter)
        binding.campusInput.setOnClickListener { binding.campusInput.showDropDown() }
        binding.campusInput.setOnItemClickListener { _, _, pos, _ ->
            vm.campus = campuses[pos]
        }

        // Clear inline errors as user types
        binding.usernameInput.doAfterTextChanged { binding.usernameInput.error = null }
        binding.passwordInput.doAfterTextChanged { binding.passwordInput.error = null }

        // --- Password toggle ---
        var isPasswordVisible = false
        binding.togglePasswordVisibility.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.togglePasswordVisibility.setColorFilter(
                    ContextCompat.getColor(requireContext(), com.example.nit3213app2.R.color.black_tint_85)
                )
            } else {
                binding.passwordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.togglePasswordVisibility.setColorFilter(
                    ContextCompat.getColor(requireContext(), com.example.nit3213app2.R.color.black_tint_55)
                )
            }
            // Move cursor to the end after toggling
            binding.passwordInput.setSelection(binding.passwordInput.text?.length ?: 0)
        }

        // --- Login button ---
        binding.loginButton.setOnClickListener {
            val u = binding.usernameInput.text?.toString()?.trim().orEmpty()
            val p = binding.passwordInput.text?.toString()?.trim().orEmpty()
            val c = binding.campusInput.text?.toString()?.trim().orEmpty()

            val errors = LoginValidator.validateLoginInput(c, u, p)

            // Show inline errors for each field
            binding.campusInput.error = errors.campusError
            binding.usernameInput.error = errors.usernameError
            binding.passwordInput.error = errors.passwordError

            if (errors.isValid) {
                // Hide any global error message
                binding.errorText.visibility = View.GONE
                // Proceed with login
                vm.login(u, p)
            }
        }

        // --- Collect states from ViewModel ---
        // Observe loading spinner visibility
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.loading.collectLatest {
                    binding.progress.visibility = if (it) View.VISIBLE else View.GONE
                }
            }
        }
        // Observe error messages from login attempt
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.error.collectLatest { msg ->
                    binding.errorText.visibility = if (msg != null) View.VISIBLE else View.GONE
                    binding.errorText.text = msg ?: ""
                }
            }
        }
        // Navigate to Dashboard when keypass is available
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.keypass.collectLatest { kp ->
                    if (!kp.isNullOrEmpty()) {
                        val args = Bundle().apply { putString("keypass", kp) }
                        findNavController().navigate(
                            com.example.nit3213app2.R.id.action_loginFragment_to_dashboardFragment,
                            args
                        )
                    }
                }
            }
        }

        return binding.root
    }

    /**
     * Clean up the binding when the view is destroyed to avoid memory leaks
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}