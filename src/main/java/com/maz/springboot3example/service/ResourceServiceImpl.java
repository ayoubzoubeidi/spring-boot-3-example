package com.maz.springboot3example.service;

import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.repository.ResourceRepository;
import com.maz.springboot3example.web.exception.NotFoundException;
import com.maz.springboot3example.web.mappers.ResourceMapper;
import com.maz.springboot3example.web.model.CreateResourceRequest;
import com.maz.springboot3example.web.model.ResourceDetails;
import com.maz.springboot3example.web.model.UpdateResourceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    @Override
    public UUID createResource(CreateResourceRequest createResourceRequest) {
        Resource resource = resourceMapper.toDomain(createResourceRequest);
        return resourceRepository.saveAndFlush(resource).getId();
    }

    @Override
    public ResourceDetails getResourceById(UUID resourceId) {
        // TODO change the runtime exception to custom exception to return RFC 7807 responses
        if (!resourceRepository.existsById(resourceId)) {
            throw new NotFoundException(String.format("Resource with id %s not found", resourceId));
        }
        return resourceRepository.findResultDetailById(resourceId).orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateResourceById(UpdateResourceRequest updateResourceRequest) {
        if (!resourceRepository.existsById(updateResourceRequest.id())) {
            throw new NotFoundException(String.format("Resource with id %s not found", updateResourceRequest.id()));
        }

        Resource resource = resourceMapper.toDomain(updateResourceRequest);
        resourceRepository.saveAndFlush(resource);
    }
}
