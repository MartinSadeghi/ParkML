package com.example.mohammadrezasadeghi.parkml;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ParkMLAPIInterface {

    @Headers("Content-Type: application/json")
    @POST("api")
    Call<ApiResponse> api(@Body Map<String, Object> body);

}
