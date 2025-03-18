package com.appliance_shop_app.shopping_cart_service.model;

import com.appliance_shop_app.shopping_cart_service.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(name = "full_price")
    private Double fullPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
