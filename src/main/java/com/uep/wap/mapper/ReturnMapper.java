package com.uep.wap.mapper;

import com.uep.wap.dto.ReturnDTO;
import com.uep.wap.model.Order;
import com.uep.wap.model.Return;

public class ReturnMapper {

    public static ReturnDTO toDTO(Return returnEntity) {
        if (returnEntity == null) {
            return null;
        }

        ReturnDTO dto = new ReturnDTO();
        dto.setReturnId(returnEntity.getReturnId());
        dto.setOrderId(returnEntity.getOrder().getOrderID());
        dto.setReturnDate(returnEntity.getReturnDate());
        dto.setStatus(returnEntity.getStatus().name());

        return dto;
    }

    public static Return toEntity(ReturnDTO dto, Order order) {
        if (dto == null) {
            return null;
        }

        Return returnEntity = new Return();
        returnEntity.setReturnId(dto.getReturnId());
        returnEntity.setOrder(order);
        returnEntity.setReturnDate(dto.getReturnDate());
        returnEntity.setStatus(Return.ReturnStatus.valueOf(dto.getStatus()));

        return returnEntity;
    }
}
