package com.example.graocafeapi.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("index.php")
    Call<ResponseBody> getItens();


}
