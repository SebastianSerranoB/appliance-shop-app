package com.appliance_shop_app.sales_service.controller;


import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.dto.SaleResponseDTO;
import com.appliance_shop_app.sales_service.repository.PaymentMethodUsage;
import com.appliance_shop_app.sales_service.service.ISaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;



    @GetMapping("/getAllSales")
    public ResponseEntity<List<SaleResponseDTO>> getAllSales(){
        return ResponseEntity.ok(this.saleService.getAllSales());
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<SaleResponseDTO> findOneById(@PathVariable Long id){
        return ResponseEntity.ok(this.saleService.findOneById(id));
    }


    @PostMapping("/completeSale")
    public ResponseEntity<?> completeSale(@Valid @RequestBody CompleteSaleRequestDTO saleDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        this.saleService.createSale(saleDTO);
        return ResponseEntity.ok("Sale completed successfully");
    }

    @PutMapping("/update-status")
    public ResponseEntity<String> updateStatus(@RequestParam Long saleId, @RequestParam String status) {
        this.saleService.editSaleStatus(saleId, status);
        return ResponseEntity.ok("Sale status updated successfully.");
    }



    @GetMapping("/topSales")
    public ResponseEntity<List<SaleResponseDTO>> topSales(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        return ResponseEntity.ok(this.saleService.getTop10SalesBetweenDates(startDate, endDate));
    }

    @GetMapping("/mostUsedPaymentMethods")
    public ResponseEntity<List<PaymentMethodUsage>> mostUsedPaymentMethods(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        return ResponseEntity.ok(this.saleService.getMostUsedPaymentMethods(startDate, endDate));
    }

    @GetMapping("/accumulatedSalesValue")
    public ResponseEntity<String> accumulatedSalesValue(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){

        return ResponseEntity.ok("The total value accumulated between the dates was: " + this.saleService.getTotalAccumulatedSales(startDate, endDate));
    }




}
