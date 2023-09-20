package com.someshvemula.orderservice.service;

import com.someshvemula.orderservice.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "API-GATEWAY")
public interface APIClient {

    @GetMapping(path = "/api/restaurants/{id}")
    RestaurantDto getRestaurant(@PathVariable("id") long id);

}
