package com.uep.wap.controller;

import com.uep.wap.dto.LogisticsDTO;
import com.uep.wap.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    @Autowired
    private LogisticsService logisticsService;

    @PostMapping
    public ResponseEntity<LogisticsDTO> addLogistics(@RequestBody LogisticsDTO logisticsDTO) {
        return ResponseEntity.ok(logisticsService.addLogistics(logisticsDTO));
    }

    @GetMapping
    public ResponseEntity<List<LogisticsDTO>> getAllLogistics() {
        List<LogisticsDTO> logisticsList = logisticsService.getAllLogistics();
        return ResponseEntity.ok(logisticsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticsDTO> getLogisticsById(@PathVariable Long id) {
        LogisticsDTO logisticsDTO = logisticsService.getLogisticsById(id);
        return ResponseEntity.ok(logisticsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogisticsDTO> updateLogistics(@PathVariable Long id, @RequestBody LogisticsDTO logisticsDTO) {
        return ResponseEntity.ok(logisticsService.updateLogistics(id, logisticsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogistics(@PathVariable Long id) {
        logisticsService.deleteLogistics(id);
        return ResponseEntity.noContent().build();
    }
}
