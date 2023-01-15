package com.fstt.gestioncommerciale.product;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fstt.gestioncommerciale.R;
import com.fstt.gestioncommerciale.authentication.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductActivity extends AppCompatActivity {
    private EditText mProductName;
    private EditText mProductPrice;
    private Button mAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mProductName = (EditText) findViewById(R.id.product_name);
        mProductPrice = (EditText) findViewById(R.id.product_price);
        mAddProduct = (Button) findViewById(R.id.add_product);

        mAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = mProductName.getText().toString();
                String productPrice = mProductPrice.getText().toString();

                System.out.println("Données " + productName + " " + productPrice);
                // Envoi des données du produit au serveur
                sendProductDataToServer(productName, productPrice);
            }
        });
    }

    private void sendProductDataToServer(String productName, String productPrice) {
        // Initialisez votre service de produit ici
        ProductService productService = NetworkService.getInstance().getProductService();
        ProductRequest productRequest = new ProductRequest(productName, productPrice);
        Call<ProductResponse> call = productService.addProduct(productRequest);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    // traitement de la réponse du serveur
                    ProductResponse productResponse = response.body();
                    // informer l'utilisateur de l'état de l'ajout du produit
                } else {
                    // gérer les erreurs de la requête
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                // gérer les erreurs de connexion
            }
        });
    }
}
