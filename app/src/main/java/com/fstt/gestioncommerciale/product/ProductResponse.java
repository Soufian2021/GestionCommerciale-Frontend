package com.fstt.gestioncommerciale.product;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("product")
    private Product product;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Product getProduct() {
        return product;
    }
}
