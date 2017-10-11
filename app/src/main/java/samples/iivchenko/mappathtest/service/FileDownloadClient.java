package samples.iivchenko.mappathtest.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import samples.iivchenko.mappathtest.models.RouteModel;

public interface FileDownloadClient {
    @Streaming
    @GET("route.txt")
    Call<RouteModel> downloadFile();
}
