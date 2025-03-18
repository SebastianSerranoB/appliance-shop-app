package com.appliance_shop_app.shopping_cart_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ShoppingCartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private ShoppingCart cart;

    @Column(name = "id_product", nullable = false)
    private Long productId;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "product_brand", nullable = false)
    private String productBrand;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotNull
    @Positive
    private int quantity;

    @NotNull
    @Positive
    @Column(name = "unit_price")
    private Double unitPrice;

}
