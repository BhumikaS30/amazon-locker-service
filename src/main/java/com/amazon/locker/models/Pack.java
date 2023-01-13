package com.amazon.locker.models;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pack {

    private String id;

    private String orderId;

    private List<Item> items;

    private double packageSize;

    public Pack(String orderId, List<Item> items) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.items = items;
        pack();
    }

    private void pack() {
        this.packageSize = Math.random() * 10;
    }
}
