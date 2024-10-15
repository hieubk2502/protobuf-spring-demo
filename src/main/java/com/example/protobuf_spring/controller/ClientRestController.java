package com.example.protobuf_spring.controller;

import com.example.protobuf_spring.model.SpaceShipProto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ClientRestController {

    RestTemplate restTemplate;

    @GetMapping(value="/api/client")
    public String ship() {
        SpaceShipProto.SpaceShip spaceship = restTemplate
                .getForObject("http://localhost:8080/api", SpaceShipProto.SpaceShip.class);
        log.info("spaceship: captain {}, fuel {}, model {}, cargo {}", spaceship.getCaptain(), spaceship.getFuel(),
                spaceship.getMode(), spaceship.getCargo());
        return "ship received..";
    }

    @GetMapping(value="/api/client/bytes")
    public byte[] shipBytes() {
        RequestEntity<byte[]> requestEntity = new RequestEntity<>(null,HttpMethod.GET,
                URI.create("http://localhost:8080/api"),
                byte[].class);
        RestTemplate restTemplateThatDoesntKnowAboutProtobuf = new RestTemplate();
        ResponseEntity<byte[]> exchange = restTemplateThatDoesntKnowAboutProtobuf
                .exchange(requestEntity, byte[].class);
        log.info("spaceship: Number of bytes {}", exchange.getBody().length);
        for (byte b : exchange.getBody()){
            log.info("Wow a byte: " + b);
        }
        return exchange.getBody();
    }

}
