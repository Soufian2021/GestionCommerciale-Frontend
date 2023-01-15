package com.fstt.gestioncommerciale.authentication;

import com.fstt.gestioncommerciale.product.ProductService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final String BASE_URL = "https://localhost/api/auth/signup";
    private static NetworkService sInstance;
    private Retrofit mRetrofit;
    private SignupService mSignupService;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSignupService = mRetrofit.create(SignupService.class);
    }

    public static NetworkService getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkService();
        }
        return sInstance;
    }

    public SignupService getSignupService() {
        return mSignupService;
    }

    public ProductService getProductService() {
        return null;
    }
}

