package com.dct.library_managment_system.controller;

import com.dct.library_managment_system.dto.FinePaidRequest;
import com.dct.library_managment_system.entity.Fine;
import com.dct.library_managment_system.service.FineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fine")
@Tag(name = "Fine API", description = "to show and pay fine")
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Fine>> fetchAllFine() {
        return ResponseEntity.ok(fineService.fetchAllFine());
    }

    @GetMapping("/id")
    public ResponseEntity<Fine> fetchFineById(@RequestParam long id) {
        return ResponseEntity.ok(fineService.fetchFineById(id));
    }

    @PutMapping("/pay/id")
    public ResponseEntity<Fine> finePaid(@RequestParam long id,
                                         @RequestBody FinePaidRequest request) {
        return ResponseEntity.ok(fineService.paidFine(id, request.getPayment()));
    }
}
