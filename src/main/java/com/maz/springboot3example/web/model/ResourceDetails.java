package com.maz.springboot3example.web.model;

import com.maz.springboot3example.domain.ResourceType;

import java.util.UUID;

public record ResourceDetails(UUID id, String name, Integer capacity, String resourceType) {
    public ResourceDetails(UUID id, String name, Integer capacity, ResourceType resourceType) {
        this(id, name, capacity, resourceType.toString());
    }
}
