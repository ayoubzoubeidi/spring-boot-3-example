package com.maz.springboot3example.web.controllers;

import com.maz.springboot3example.service.ResourceService;
import com.maz.springboot3example.web.model.CreateOrUpdateResourceRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@Controller
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<?> createResource(@RequestBody CreateOrUpdateResourceRequest createOrUpdateResourceRequest) {

        UUID createdResourceId = resourceService.createResource(createOrUpdateResourceRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdResourceId.toString())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<?> getResourceById(@PathVariable UUID resourceId) {
        return ResponseEntity.ok(resourceService.getResourceById(resourceId));
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<?> updateResource(@PathVariable UUID resourceId, @RequestBody CreateOrUpdateResourceRequest createOrUpdateResourceRequest) {
        throw new NotImplementedException();
    }
}
