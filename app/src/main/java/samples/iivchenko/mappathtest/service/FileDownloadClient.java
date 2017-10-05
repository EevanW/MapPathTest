package samples.iivchenko.mappathtest.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FileDownloadClient {
    @GET("route.txt")
    Call<ResponseBody> downloadFile();
}
