package com.giles.cibd.ui.cellSite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giles.cibd.R
import com.giles.cibd.databinding.CellSiteFragmentBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MapStyleOptions
import timber.log.Timber

class CellSiteFragment : Fragment(){
    private var mBinding: CellSiteFragmentBinding? = null
    private var mGoogleMap: GoogleMap? = null

    companion object {
        val TAG = "CellSiteFragment"

        fun newInstance() = CellSiteFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = CellSiteFragmentBinding.inflate(inflater, container, false)

        mBinding?.btnTestChange?.setOnClickListener{
            val success = mGoogleMap?.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.map_style_all))

        }

        initMapvView(savedInstanceState)
        return mBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    fun initMapvView(savedInstanceState: Bundle?){
        MapsInitializer.initialize(context)
        mBinding?.mapView?.onCreate(savedInstanceState)
        mBinding?.mapView?.getMapAsync(OnMapReadyCallback { googleMap ->
            Timber.d("onMapReady")

//            googleMap.mapType = (GoogleMap.MAP_TYPE_NONE)
            mGoogleMap = googleMap
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.map_style_remove_labels))

            Timber.d("success = $success")

//            val nkut = LatLng(25.076780, 121.574235)
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nkut, 17f))


            mBinding?.mapView?.onResume()
        })
    }

}