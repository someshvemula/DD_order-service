package com.someshvemula.orderservice.dto;

import com.someshvemula.orderservice.enums.Cuisine;
import com.someshvemula.orderservice.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private long id;
    private String name;
    private Cuisine cuisine;
    private String address;
    private float rating;
    private long contactNumber;
    private String website;
    private int averageDeliveryTimeInMinutes;
    private int deliveryFee;
    private int minimumOrderAmount;
    private Currency currencyUsed;
}
