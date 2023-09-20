package com.someshvemula.orderservice.controller;

import com.someshvemula.orderservice.dto.ApiResponseDto;
import com.someshvemula.orderservice.dto.RequestDto;
import com.someshvemula.orderservice.dto.ResponseDto;
import com.someshvemula.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody RequestDto requestDto){
        ResponseDto responseDto = orderService.createOrder(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getOrder(@PathVariable("id") long orderId){
        ApiResponseDto apiResponseDto = orderService.getOrder(orderId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ResponseDto>> getRestaurantsByRestaurantId(@PathVariable("restaurantId") long restaurantId){
        return new ResponseEntity<>(orderService.getOrdersByRestaurantId(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResponseDto>> getRestaurantsByUserId(@PathVariable("userId") long userId){
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseDto>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateOrderStatus(@PathVariable("id") long orderId, @RequestParam String orderStatus){
        ResponseDto modifiedOrder = orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>(modifiedOrder, HttpStatus.OK);
    }
}
