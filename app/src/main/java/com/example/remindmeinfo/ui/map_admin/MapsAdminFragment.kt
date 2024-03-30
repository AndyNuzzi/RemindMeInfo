package com.example.remindmeinfo.ui.map_admin

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remindmeinfo.R
import com.example.remindmeinfo.ui.calendar_user.placeholder.PlaceholderContent.currentUser
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore


class MapsAdminFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        var email = currentUser?.email.toString()
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(email)
            .get()
            .addOnSuccessListener { doc ->
                if (doc != null) {
                    val user_elder = doc.getString("user_elder")

                    if (user_elder != null) {
                        db.collection("users").document(user_elder)
                            .get()
                            .addOnSuccessListener {docs ->
                                val latitud = docs.get("latitud")
                                val longitud = docs.get("longitud")

                                val user_loc = LatLng(latitud as Double, longitud as Double)
                                googleMap.addMarker(MarkerOptions().position(user_loc).title("Marcador del Usuario"))
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_loc, 15f))

                            }
                    }
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}