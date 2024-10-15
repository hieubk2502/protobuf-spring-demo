package com.example.protobuf_spring.controller;

import com.example.protobuf_spring.model.SpaceShipProto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceRestController {

    @GetMapping(value = "/api", produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    public SpaceShipProto.SpaceShip ship() {
        return SpaceShipProto.SpaceShip.newBuilder()
                .setCaptain("Hieu")
                .setCargo(SpaceShipProto.SpaceShip.CargoType.FRUIT)
                .setMode("short")
                .setFuel(5)
                .build();
    }

}
