package com.maz.springboot3example.service;

import com.maz.springboot3example.web.model.CreateOrUpdateResourceRequest;
import com.maz.springboot3example.web.model.ResourceDetails;

import java.util.UUID;

public interface ResourceService {
    UUID createResource(CreateOrUpdateResourceRequest createOrUpdateResourceRequest);
    ResourceDetails getResourceById(UUID resourceId);
}
