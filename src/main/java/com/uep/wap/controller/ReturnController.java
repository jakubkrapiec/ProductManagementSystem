package com.uep.wap.controller;

import com.uep.wap.dto.ReturnDTO;
import com.uep.wap.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returns")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @PostMapping
    public ResponseEntity<ReturnDTO> addReturn(@RequestBody ReturnDTO dto) {
        return ResponseEntity.status(201).body(returnService.addReturn(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReturnDTO>> getAllReturns() {
        List<ReturnDTO> returns = returnService.getAllReturns();
        return ResponseEntity.ok(returns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnDTO> getReturnById(@PathVariable Long id) {
        ReturnDTO dto = returnService.getReturnById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnDTO> updateReturn(@PathVariable Long id, @RequestBody ReturnDTO dto) {
        return ResponseEntity.ok(returnService.updateReturn(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReturn(@PathVariable Long id) {
        returnService.deleteReturn(id);
        return ResponseEntity.noContent().build();
    }
}
