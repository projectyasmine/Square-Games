package com.squaregames.demo.controller;

import com.squaregames.demo.service.HeartbeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {

        @Autowired
        private HeartbeatSensor heartbeatSensor ;

    @GetMapping("/heartbeat")
    public int GetHeartbeat() {

        return this.heartbeatSensor.get();
    }
}
