package com.maz.springboot3example.service;

import com.maz.springboot3example.web.model.BookResourceRequest;

import java.util.UUID;

public interface BookingService {
    UUID bookResources(BookResourceRequest bookResourceRequest);
}
