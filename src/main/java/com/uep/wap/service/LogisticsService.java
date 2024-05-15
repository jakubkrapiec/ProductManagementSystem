package com.uep.wap.service;

import com.uep.wap.dto.LogisticsDTO;
import com.uep.wap.mapper.LogisticsMapper;
import com.uep.wap.model.Logistics;
import com.uep.wap.model.Order;
import com.uep.wap.model.Warehouse;
import com.uep.wap.repository.LogisticsRepository;
import com.uep.wap.repository.OrderRepository;
import com.uep.wap.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LogisticsService {

    @Autowired
    private LogisticsRepository logisticsRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private OrderRepository orderRepository;

    public LogisticsDTO addLogistics(LogisticsDTO logisticsDTO) {
        Warehouse warehouse = warehouseRepository.findById(logisticsDTO.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Order order = orderRepository.findById(logisticsDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Logistics logistics = LogisticsMapper.toEntity(logisticsDTO, warehouse, order);
        return LogisticsMapper.toDTO(logisticsRepository.save(logistics));
    }

    public List<LogisticsDTO> getAllLogistics() {
        return StreamSupport.stream(logisticsRepository.findAll().spliterator(), false)
                .map(LogisticsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LogisticsDTO getLogisticsById(Long id) {
        Logistics logistics = logisticsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logistics not found"));
        return LogisticsMapper.toDTO(logistics);
    }

    public LogisticsDTO updateLogistics(Long id, LogisticsDTO logisticsDTO) {
        Logistics existingLogistics = logisticsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logistics not found"));

        Warehouse warehouse = warehouseRepository.findById(logisticsDTO.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Order order = orderRepository.findById(logisticsDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Logistics updatedLogistics = LogisticsMapper.toEntity(logisticsDTO, warehouse, order);
        updatedLogistics.setShipmentId(existingLogistics.getShipmentId());
        return LogisticsMapper.toDTO(logisticsRepository.save(updatedLogistics));
    }

    public void deleteLogistics(Long id) {
        Logistics logistics = logisticsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logistics not found"));
        logisticsRepository.delete(logistics);
    }
}
