package com.someshvemula.orderservice.service.impl;

import com.someshvemula.orderservice.dto.ApiResponseDto;
import com.someshvemula.orderservice.dto.RequestDto;
import com.someshvemula.orderservice.dto.ResponseDto;
import com.someshvemula.orderservice.dto.RestaurantDto;
import com.someshvemula.orderservice.entity.Order;
import com.someshvemula.orderservice.enums.OrderStatus;
import com.someshvemula.orderservice.event.OrderPlacedEvent;
import com.someshvemula.orderservice.exception.ResourceNotFoundException;
import com.someshvemula.orderservice.repository.OrderRepository;
import com.someshvemula.orderservice.service.APIClient;
import com.someshvemula.orderservice.service.OrderService;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient webClient;

    @Autowired
    private APIClient apiClient;

    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public ResponseDto createOrder(RequestDto requestDto) {
        Order order = modelMapper.map(requestDto, Order.class);
        Order savedOrder = orderRepository.save(order);
        kafkaTemplate.send("notification-topic",
                OrderPlacedEvent.builder()
                        .recipientName("Somesh")
                        .recipientEmail("Ratansomesh@gmail.com")
                        .content("Your order has been placed")
                        .build()
        );
        return modelMapper.map(savedOrder, ResponseDto.class);
    }

    @Override
    public ApiResponseDto getOrder(long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
//        RestaurantDto restaurantDto = webClient.get()
//                .uri("http://localhost:8080/api/restaurants/"+order.getRestaurantId())
//                .retrieve()
//                .bodyToMono(RestaurantDto.class)
//                .block();
        RestaurantDto restaurantDto = apiClient.getRestaurant(order.getRestaurantId());
        return new ApiResponseDto(modelMapper.map(order, ResponseDto.class), restaurantDto);
    }

    @Override
    public List<ResponseDto> getOrdersByRestaurantId(long restaurantId) {
        List<Order> orderList = orderRepository.findByRestaurantId(restaurantId);
        List<ResponseDto> responseDtoList = new ArrayList<>();
        for(Order order : orderList){
            responseDtoList.add(modelMapper.map(order, ResponseDto.class));
        }
        return responseDtoList;
    }

    @Override
    public List<ResponseDto> getOrdersByUserId(long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
        List<ResponseDto> responseDtoList = new ArrayList<>();
        for(Order order : orderList){
            responseDtoList.add(modelMapper.map(order, ResponseDto.class));
        }
        return responseDtoList;
    }

    @Override
    public List<ResponseDto> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        List<ResponseDto> responseDtoList = new ArrayList<>();

        for(Order order : orderList){
            responseDtoList.add(modelMapper.map(order, ResponseDto.class));
        }

        return responseDtoList;
    }

    @Override
    public ResponseDto updateOrderStatus(long orderId, String orderStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        order.setOrderStatus(OrderStatus.valueOf(orderStatus));
        order.setUpdatedAt(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(order);
        return modelMapper.map(updatedOrder, ResponseDto.class);
    }



}
