package com.uep.wap.service;

import com.uep.wap.dto.ReturnDTO;
import com.uep.wap.mapper.ReturnMapper;
import com.uep.wap.model.Order;
import com.uep.wap.model.Return;
import com.uep.wap.repository.OrderRepository;
import com.uep.wap.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    @Autowired
    private OrderRepository orderRepository;

    public ReturnDTO addReturn(ReturnDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Return returnEntity = ReturnMapper.toEntity(dto, order);
        return ReturnMapper.toDTO(returnRepository.save(returnEntity));
    }

    public List<ReturnDTO> getAllReturns() {
        return StreamSupport.stream(returnRepository.findAll().spliterator(), false)
                .map(ReturnMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReturnDTO getReturnById(Long id) {
        Return returnEntity = returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));
        return ReturnMapper.toDTO(returnEntity);
    }

    public ReturnDTO updateReturn(Long id, ReturnDTO dto) {
        Return existingReturn = returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Return updatedReturn = ReturnMapper.toEntity(dto, order);
        updatedReturn.setReturnId(existingReturn.getReturnId());
        return ReturnMapper.toDTO(returnRepository.save(updatedReturn));
    }

    public void deleteReturn(Long id) {
        Return returnEntity = returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));
        returnRepository.delete(returnEntity);
    }
}
