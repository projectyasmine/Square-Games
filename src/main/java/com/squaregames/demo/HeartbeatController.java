package com.squaregames.demo;

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
