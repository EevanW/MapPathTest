package samples.iivchenko.mappathtest.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import samples.iivchenko.mappathtest.models.RouteModel;

public class FileDownloader {
    private static final String LOG_TAG = "FileDownloader";

    public void downloadFile(final Context context, final GoogleMap gMap) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://test.www.estaxi.ru/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitAnnotation retrofitAnnotation = retrofit.create(RetrofitAnnotation.class);
        Call<RouteModel> call = retrofitAnnotation.downloadFile();
        Toast.makeText(context, "Route downloading", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<RouteModel>() {
            @Override
            public void onResponse(Call<RouteModel> call, Response<RouteModel> response) {
                new PolyLinePainter().paint(gMap, response.body());
            }

            @Override
            public void onFailure(Call<RouteModel> call, Throwable e) {
                Toast.makeText(context, "download was failed", Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, e.toString());
            }
        });
    }
}