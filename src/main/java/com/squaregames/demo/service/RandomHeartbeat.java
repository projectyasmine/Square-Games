package com.squaregames.demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomHeartbeat implements HeartbeatSensor {

    private final Random rand = new Random();

    @Override
    public int get() {
        return this.rand.nextInt(100);
    }
}
