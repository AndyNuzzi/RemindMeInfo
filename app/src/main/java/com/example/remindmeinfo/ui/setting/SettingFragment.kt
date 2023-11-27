package com.example.remindmeinfo.ui.setting

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentSettingBinding
import android.provider.Settings
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private lateinit var seekBarBrightness: SeekBar
    private lateinit var seekBarContrast: SeekBar
    private lateinit var switchNightMode: Switch

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        seekBarBrightness = view.findViewById(R.id.seekBarBright)
        seekBarContrast = view.findViewById(R.id.seekBarContrast)
        switchNightMode = view.findViewById(R.id.switch1)

        // Solicitar permisos WRITE_SETTINGS si es necesario
        requestWriteSettingsPermission()

        val layoutParams = requireActivity().window.attributes

        // Configurar el Listener para la SeekBarBright
        seekBarBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val brightnessValue = progress / 100.0f

                // Ajustar el brillo de la pantalla
                layoutParams.screenBrightness = brightnessValue
                requireActivity().window.attributes = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        // configurar el Listener para la SeekBarContrast
        seekBarContrast.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Calcular el valor de contraste en base al progreso de la SeekBar
                val contrastValue = progress / 100f

                // Actualizar el contraste de la pantalla
                layoutParams.screenBrightness = contrastValue
                requireActivity().window.attributes = layoutParams

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        switchNightMode.isChecked = when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            else -> false
        }

        // Configurar el listener para el Switch
        switchNightMode.setOnCheckedChangeListener { _, isChecked ->
            // Cambiar dinámicamente el modo noche según la posición del Switch
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // Recrear la actividad para aplicar el cambio de modo noche
            requireActivity().recreate()
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestWriteSettingsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(requireContext())) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:" + requireContext().packageName)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}