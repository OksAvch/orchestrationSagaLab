package com.oksavch.payment.controller;


import com.oksavch.payment.dto.BalanceResponseDTO;
import com.oksavch.payment.dto.PaymentRequestDTO;
import com.oksavch.payment.dto.PaymentResponseDTO;
import com.oksavch.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@AllArgsConstructor
public class PaymentController {

    private PaymentService service;

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO) {
        return service.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDTO requestDTO) {
        service.credit(requestDTO);
    }

    @GetMapping("/balance/{userId}")
    public BalanceResponseDTO credit(@PathVariable int userId) {
        return service.getBalance(userId);
    }

}
