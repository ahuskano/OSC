package co.ahuskano.something.fragments;

import android.location.Location;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import co.ahuskano.something.R;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentMapa extends BaseFragment implements OnMapReadyCallback {

    public FragmentMapa() {
        super(FragmentFactory.FRAGMENT_MAP);
    }

    @Override
    public void initViews(View view) {
        showDialog();
        SupportMapFragment mapFragment = new SupportMapFragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, mapFragment).commit();
        mapFragment.getMapAsync(this);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        SmartLocation.with(getActivity()).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        if (location != null) {
                            googleMap.addMarker(
                                    new MarkerOptions().position(
                                            new LatLng(
                                                    Double.valueOf(location.getLatitude()),
                                                    Double.valueOf(location.getLongitude())))
                                            .title("Your location"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));
                            dismissDialog();
                        }

                    }
                });

    }
}
