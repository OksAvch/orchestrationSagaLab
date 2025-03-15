package com.oksavch.inventory.controller;

import com.oksavch.inventory.dto.InventoryRequestDTO;
import com.oksavch.inventory.dto.InventoryResponseDTO;
import com.oksavch.inventory.dto.QtyResponseDTO;
import com.oksavch.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inventory")
@AllArgsConstructor
public class InventoryController {

    private InventoryService service;

    @PostMapping("/deduct")
    public InventoryResponseDTO deduct(@RequestBody final InventoryRequestDTO requestDTO) {
        return this.service.deductInventory(requestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody final InventoryRequestDTO requestDTO) {
        this.service.addInventory(requestDTO);
    }

    @GetMapping("/{productId}")
    public QtyResponseDTO add(@PathVariable int productId) {
        return this.service.getInventory(productId);
    }

}
