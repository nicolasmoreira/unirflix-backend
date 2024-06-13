package com.unir.rents.controller;

import com.unir.rents.model.db.Purchase;
import com.unir.rents.model.db.Rating;
import com.unir.rents.model.db.Rent;
import com.unir.rents.model.request.PurchaseRequest;
import com.unir.rents.model.request.RentRequest;
import com.unir.rents.service.PurchasesService;
import com.unir.rents.service.PurchasesServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PurchaseController {
    private final PurchasesService service; //Inyeccion por constructor mediante @RequiredArgsConstructor. Y, también es inyección por interfaz.

    @PostMapping("/purchases")
    public ResponseEntity<Purchase> purchase(@RequestBody @Valid PurchaseRequest request) { //Se valida con Jakarta Validation API

        Purchase purchase = service.purchase(request);

        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getPurchases() {

        List<Purchase> purchases = service.getPurchases();
        if (purchases != null) {
            return ResponseEntity.ok(purchases);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getPurchase(@PathVariable String id) {

        Purchase purchase = service.getPurchase(id);
        if (purchase!= null) {
            return ResponseEntity.ok(purchase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
