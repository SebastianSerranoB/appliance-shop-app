package com.appliance_shop_app.products_service.repository;

import com.appliance_shop_app.products_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.name = :name AND p.brand = :brand")
    boolean existsByNameAndBrand(@Param("name") String name, @Param("brand") String brand);

    boolean existsByName(String name);

}
