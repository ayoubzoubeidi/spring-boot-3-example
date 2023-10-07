package com.maz.springboot3example.web.model;

public record CreateOrUpdateResourceRequest(String name, Integer capacity, String type) {
}
