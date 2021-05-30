package com.example.mypoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mypoi.databinding.ActivityMapShowPosizioneBinding

class MapShowPosizione : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapShowPosizioneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapShowPosizioneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map2) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val lat = intent.getStringExtra("lat")
        val lon = intent.getStringExtra("lon")
        val sydney = LatLng(lat!!.toDouble(), lon!!.toDouble())
        mMap.addMarker(MarkerOptions().position(sydney).title("La mia Posizione")).showInfoWindow()
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}