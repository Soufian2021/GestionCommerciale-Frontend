package com.fstt.gestioncommerciale.product;

import com.fstt.gestioncommerciale.authentication.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class ProductService {
    private static final String BASE_URL = "https://api/addProduct/";
    private Retrofit retrofit;
    private ProductAPI productAPI;

    public ProductService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productAPI = retrofit.create(ProductAPI.class);
    }

    public void getAllProducts(final ProductCallback callback) {
        Call<List<Product>> call = productAPI.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public void addProduct(Product product, final ProductCallback callback) {
        Call<Product> call = productAPI.addProduct(product);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public Call<ProductResponse> addProduct(ProductRequest productRequest) {
        // utilisation de Retrofit pour créer un objet de type ProductService
        ProductService productService = NetworkService.getInstance().getProductService();
        // appel de la méthode addProduct() de l'interface ProductService en passant en paramètre l'objet ProductRequest
        Call<ProductResponse> call = productService.addProduct(productRequest);
        // retour de l'objet Call<ProductResponse>
        return call;
    }








    public interface ProductAPI {
        @GET("products")
        Call<List<Product>> getAllProducts();

        @POST("products")
        @FormUrlEncoded
        Call<Product> addProduct(@Field("name") String name, @Field("price") double price);

        Call<Product> addProduct(Product product);
    }

    public interface ProductCallback {
        void onSuccess(Object object);
        void onError();
    }
}
