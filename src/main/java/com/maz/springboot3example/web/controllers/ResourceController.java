package com.maz.springboot3example.web.controllers;

import com.maz.springboot3example.service.ResourceService;
import com.maz.springboot3example.web.model.CreateResourceRequest;
import com.maz.springboot3example.web.model.UpdateResourceRequest;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> createResource(@RequestBody CreateResourceRequest createResourceRequest) {

        UUID createdResourceId = resourceService.createResource(createResourceRequest);
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

    @PutMapping
    public ResponseEntity<?> updateResource(@RequestBody UpdateResourceRequest updateResourceRequest) {
        resourceService.updateResourceById(updateResourceRequest);
        return ResponseEntity.noContent().build();
    }
}
