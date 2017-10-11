package samples.iivchenko.mappathtest.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import samples.iivchenko.mappathtest.R;
import samples.iivchenko.mappathtest.models.RouteModel;
import samples.iivchenko.mappathtest.service.FileDownloadClient;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final int MY_PERMISSION_REQUEST = 300;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST);
        }
        final Button downloadButton = (Button) findViewById(R.id.button2);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile();
            }
        });
    }

    private void downloadFile() {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://test.www.estaxi.ru/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        FileDownloadClient fileDownloadClient = retrofit.create(FileDownloadClient.class);
        Call<RouteModel> call = fileDownloadClient.downloadFile();


        call.enqueue(new Callback<RouteModel>() {
            @Override
            public void onResponse(Call<RouteModel> call, Response<RouteModel> response) {
                new PolyLinePainter().paint(getApplicationContext(), mMap, response.body());
                Toast.makeText(MapsActivity.this, "download was success: ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RouteModel> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(54.8, 73.1);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Omsk"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
