package com.someshvemula.orderservice.dto;

import com.someshvemula.orderservice.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private long id;
    private long userId;
    private long restaurantId;
    private float totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String deliveryAddress;
    private OrderStatus orderStatus;
}
