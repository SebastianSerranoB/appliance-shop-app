package com.appliance_shop_app.sales_service.repository;

import com.appliance_shop_app.sales_service.model.Sale;
import com.appliance_shop_app.sales_service.model.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {


    @Query("SELECT s FROM Sale s WHERE s.status = :status AND s.date BETWEEN :startDate AND :endDate ORDER BY s.fullPrice DESC")
    List<Sale> findTop10SalesBetweenDates(@Param("status") Status status,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate,
                                          Pageable pageable);






    @Query("SELECT s.paymentMethod, COUNT(s) FROM Sale s " +
            "WHERE s.status = :status AND s.date BETWEEN :startDate AND :endDate " +
            "GROUP BY s.paymentMethod ORDER BY COUNT(s) DESC")
    List<Object[]> findMostUsedPaymentMethod(@Param("status") Status status,
                                       @Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);





    @Query("SELECT SUM(s.fullPrice) FROM Sale s WHERE s.status = :status AND s.date BETWEEN :startDate AND :endDate")
    Double findTotalAccumulatedSales(@Param("status") Status status,
                                     @Param("startDate") LocalDateTime startDate,
                                     @Param("endDate") LocalDateTime endDate);



}
