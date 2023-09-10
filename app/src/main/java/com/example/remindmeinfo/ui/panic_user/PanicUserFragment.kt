package com.example.remindmeinfo.ui.panic_user

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.remindmeinfo.databinding.FragmentPanicUserBinding

class PanicUserFragment : Fragment() {

    private var _binding: FragmentPanicUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPanicUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val imageView: ImageView = binding.ImageButton
        imageView.setOnClickListener{
            requestPermissions()
        }
        return root
    }

    private fun requestPermissions() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when {
                getContext()?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        android.Manifest.permission.CALL_PHONE
                    )
                } == PackageManager.PERMISSION_GRANTED -> {
                    call()
                }
                else -> requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
            }
        } else{
            call()
        }
    }

    private fun call(){
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel: 603636097")))
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if (isGranted) call()
        else Toast.makeText(requireContext(), "No se han concedido los permisos necesarios", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}