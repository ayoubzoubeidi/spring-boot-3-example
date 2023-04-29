package com.maz.springboot3example.web.model;

public record CreateResourceRequest(String name, Integer capacity, String resourceType) {
}
