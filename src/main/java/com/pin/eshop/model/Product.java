package com.pin.eshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String title;
    private String description;
    private String imageUri;
    private Number price;

    public Product() {

    }

    public Product(Product product) {
        this.title = product.title;
        this.description = product.description;
        this.imageUri = product.imageUri;
        this.price = product.price;
    }

    public Product(String title, String description, String imageUri) {
        this.title = title;
        this.description = description;
        this.imageUri = imageUri;
    }

    public Product updateNotNulls(Product product) {
        if (product.title != null) this.title = product.title;
        if (product.description != null) this.description = product.description;
        if (product.imageUri != null) this.imageUri = product.imageUri;
        if (product.price != null) this.price = product.price;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUri='" + imageUri + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}