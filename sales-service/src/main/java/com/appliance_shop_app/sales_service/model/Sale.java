package com.appliance_shop_app.sales_service.model;

import com.appliance_shop_app.sales_service.model.enums.PaymentMethod;
import com.appliance_shop_app.sales_service.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Positive
    @Column(name = "cart_id")
    private Long cartId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @PastOrPresent
    private LocalDateTime date;

    @Positive
    @Column(name = "full_price", nullable = true)
    private Double fullPrice;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_surname")
    private String clientSurname;

    @Column(name = "client_identification")
    private String clientIdentification;





}
