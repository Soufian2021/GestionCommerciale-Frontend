package com.fstt.gestioncommerciale.authentication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignupService {
    @POST("signup")
    Call<SignupResponse> signup(@Body SignupRequest request);
}

