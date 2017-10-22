package samples.iivchenko.mappathtest.ui;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samples.iivchenko.mappathtest.models.RouteModel;
import samples.iivchenko.mappathtest.service.PolyLinePainter;
import samples.iivchenko.mappathtest.service.RetrofitImplementation;

public class IndicateDownloading {
    private static final String LOG_TAG = "IndicateDownloading";

    public void downloadFile(final Context context, final GoogleMap gMap) {
        RetrofitImplementation retrofitImplementation = new RetrofitImplementation();
        Call<RouteModel> call = retrofitImplementation.getFileFromServer();
        Toast.makeText(context, "Route downloading", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<RouteModel>() {
            @Override
            public void onResponse(Call<RouteModel> call, Response<RouteModel> response) {
                new PolyLinePainter().paint(gMap, response.body());
            }

            @Override
            public void onFailure(Call<RouteModel> call, Throwable e) {
                Toast.makeText(context, "download was failed", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, e.toString());
            }
        });
    }

}
