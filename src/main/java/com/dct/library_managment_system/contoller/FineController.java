package com.dct.library_managment_system.contoller;

import com.dct.library_managment_system.dto.FinePaidRequest;
import com.dct.library_managment_system.entity.Fine;
import com.dct.library_managment_system.service.FineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping("/fine")
    public ResponseEntity<List<Fine>> fetchAllFine() {
        return ResponseEntity.ok(fineService.fetchAllFine());
    }

    @GetMapping("/fine/id")
    public ResponseEntity<Fine> fetchFineById(@RequestParam long id) {
        return ResponseEntity.ok(fineService.fetchFineById(id));
    }

    @PutMapping("/fine/pay/id")
    public ResponseEntity<Fine> finePaid(@RequestParam long id,
                                         @RequestBody FinePaidRequest request) {
        return ResponseEntity.ok(fineService.paidFine(id, request.getPayment()));
    }
}
