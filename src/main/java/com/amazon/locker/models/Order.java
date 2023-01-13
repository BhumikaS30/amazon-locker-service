package com.amazon.locker.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private String id;

    private List<Item> items;

    private GeoLocation deliveryGeoLocation;

}
