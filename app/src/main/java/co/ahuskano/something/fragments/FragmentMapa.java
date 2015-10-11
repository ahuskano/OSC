package co.ahuskano.something.fragments;

import android.content.Intent;
import android.location.Location;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import co.ahuskano.something.R;
import co.ahuskano.something.activitys.SpaceDetailActivity;
import co.ahuskano.something.api.BaseResponse;
import co.ahuskano.something.api.SpacesResponse;
import co.ahuskano.something.controllers.BaseController;
import co.ahuskano.something.controllers.SpacesController;
import co.ahuskano.something.models.Space;
import co.ahuskano.something.util.Constants;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by ahuskano on 10.10.2015..
 */
public class FragmentMapa extends BaseFragment implements OnMapReadyCallback, BaseController.OnDataReadListener{

    public FragmentMapa() {
        super(FragmentFactory.FRAGMENT_MAP);
    }
    private GoogleMap map;

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
        map=googleMap;
        SmartLocation.with(getActivity()).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        if (location != null) {
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12));
                            dismissDialog();
                            map.addMarker(
                                    new MarkerOptions().position(
                                            new LatLng(
                                                    Double.valueOf(location.getLatitude()),
                                                    Double.valueOf(location.getLongitude())))
                                            .title("My location").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_my_location_smaller)));
                        }

                    }
                });

        SpacesController spaces=new SpacesController(getActivity());
        spaces.setOnDataReadListener(this);
        spaces.getSpaces();
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                if(arg0.getSnippet()!=null) {
                    Intent intent = new Intent(getActivity(), SpaceDetailActivity.class);
                    intent.putExtra(Constants.KEY_SPACE_ID, arg0.getSnippet().split("#")[0]);
                    intent.putExtra(Constants.KEY_SPACE_NAME, arg0.getSnippet().split("#")[1]);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDataReceive(BaseResponse response) {
        if(map!=null) {
            SpacesResponse spaces = (SpacesResponse) response;
            for(Space space:spaces.getData()) {
                map.addMarker(
                        new MarkerOptions().position(
                                new LatLng(
                                        Double.valueOf(space.getLat()),
                                        Double.valueOf(space.getLongitude())))
                                .title(space.getName()).snippet(space.getId()+"#"+space.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_map_smaller)));
            }
        }
    }
}
