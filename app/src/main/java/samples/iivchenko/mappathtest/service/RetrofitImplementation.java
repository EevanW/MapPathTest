package samples.iivchenko.mappathtest.service;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import samples.iivchenko.mappathtest.models.RouteModel;

public class RetrofitImplementation {
    private static final String LOG_TAG = "RetrofitImplementation";

    public Call<RouteModel> getFileFromServer() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://test.www.estaxi.ru/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitAnnotation retrofitAnnotation = retrofit.create(RetrofitAnnotation.class);
        return retrofitAnnotation.downloadFile();
    }
}