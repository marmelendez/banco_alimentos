package mx.iv.bancodealimentos_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mx.iv.bancodealimentos_project.R


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private lateinit var map : GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val supportMapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0

        val foodBank = LatLng(20.65627693383698, -103.35532951647889)
        map.addMarker(MarkerOptions().position(foodBank).title("Banco de alimentos de Guadalajara"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(foodBank, 18f))
        map.setOnMapClickListener(this)
    }

    override fun onMapClick(p0: LatLng) {
        map.addMarker(
            MarkerOptions()
                .position(p0)
                .title("Marcador creado dinámicamente")
                .alpha(0.5f)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        )
    }
}