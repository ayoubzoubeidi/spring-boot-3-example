package com.maz.springboot3example.web.model;

import java.util.UUID;

public record UpdateResourceRequest(UUID id, String name, Integer capacity, String type) {
}
