package com.maz.springboot3example.service;

import com.maz.springboot3example.web.model.CreateResourceRequest;
import com.maz.springboot3example.web.model.ResourceDetails;

import java.util.UUID;

public interface ResourceService {
    UUID createResource(CreateResourceRequest createResourceRequest);
    ResourceDetails getResourceById(UUID resourceId);
}
