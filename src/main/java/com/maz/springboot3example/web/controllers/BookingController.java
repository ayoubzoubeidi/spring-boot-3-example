package com.maz.springboot3example.web.controllers;

import com.maz.springboot3example.service.BookingService;
import com.maz.springboot3example.web.model.BookResourceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> bookResources(@RequestBody BookResourceRequest bookResourceRequest) {

        UUID bookingId = bookingService.bookResources(bookResourceRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookingId.toString())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
