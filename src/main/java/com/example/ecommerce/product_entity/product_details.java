package com.example.ecommerce.product_entity;


import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;

@Entity
@Table(name = "details")
public class product_details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="product_Name")
    private String productName;
    @Column(name="product_details")
    private String productDetails;
    @Column(name="product_price")
    private float productPrice;
    @Column(name = "product_stock")
    private int productStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return "product_details{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", prodtctDetails='" + productDetails + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                '}';
    }
}
