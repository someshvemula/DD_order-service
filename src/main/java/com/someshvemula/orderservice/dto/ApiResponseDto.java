package com.someshvemula.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// this
public class ApiResponseDto {
    private ResponseDto order;
    private RestaurantDto restaurant;
}
