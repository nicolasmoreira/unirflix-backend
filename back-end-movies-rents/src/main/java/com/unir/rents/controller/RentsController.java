package com.unir.rents.controller;

import com.unir.rents.model.db.Rent;
import com.unir.rents.model.request.ReturnRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.rents.model.request.RentRequest;
import com.unir.rents.service.RentsService;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RentsController {

    private final RentsService service; //Inyeccion por constructor mediante @RequiredArgsConstructor. Y, también es inyección por interfaz.

    @PostMapping("/rents")
    public ResponseEntity<Rent> createRent(@RequestBody @Valid RentRequest request) { //Se valida con Jakarta Validation API

        log.info("Creating a new rent...");
        Rent created = service.createRent(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/returns")
    public ResponseEntity<Rent> createRent(@RequestBody @Valid ReturnRequest request) { //Se valida con Jakarta Validation API

        Rent rent = service.returnRented(request);

        if (rent != null) {
            return ResponseEntity.ok(rent);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rents")
    public ResponseEntity<List<Rent>> getRent() {

        List<Rent> rents = service.getRents();
        if (rents != null) {
            return ResponseEntity.ok(rents);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/rents/{id}")
    public ResponseEntity<Rent> getRent(@PathVariable String id) {

        Rent rent = service.getRent(id);
        if (rent != null) {
            return ResponseEntity.ok(rent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
