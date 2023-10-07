package com.maz.springboot3example.service;

import com.maz.springboot3example.web.model.CreateResourceRequest;
import com.maz.springboot3example.web.model.ResourceDetails;
import com.maz.springboot3example.web.model.UpdateResourceRequest;

import java.util.UUID;

public interface ResourceService {
    UUID createResource(CreateResourceRequest createResourceRequest);
    ResourceDetails getResourceById(UUID resourceId);
    void updateResourceById(UpdateResourceRequest updateResourceRequest);
}
