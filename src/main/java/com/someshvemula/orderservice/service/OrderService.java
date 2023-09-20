package com.someshvemula.orderservice.service;

import com.someshvemula.orderservice.dto.ApiResponseDto;
import com.someshvemula.orderservice.dto.RequestDto;
import com.someshvemula.orderservice.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    ResponseDto createOrder(RequestDto requestDto);

    ApiResponseDto getOrder(long orderId);

    List<ResponseDto> getOrdersByRestaurantId(long restaurantId);

    List<ResponseDto> getOrdersByUserId(long userId);

    List<ResponseDto> getAllOrders();

    ResponseDto updateOrderStatus(long orderId, String orderStatus);
}
