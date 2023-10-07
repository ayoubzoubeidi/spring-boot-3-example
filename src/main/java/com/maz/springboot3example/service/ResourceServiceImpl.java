package com.maz.springboot3example.service;

import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.repository.ResourceRepository;
import com.maz.springboot3example.web.mappers.ResourceMapper;
import com.maz.springboot3example.web.model.CreateOrUpdateResourceRequest;
import com.maz.springboot3example.web.model.ResourceDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    @Override
    public UUID createResource(CreateOrUpdateResourceRequest createOrUpdateResourceRequest) {
        Resource resource = resourceMapper.toDomain(createOrUpdateResourceRequest);
        return resourceRepository.saveAndFlush(resource).getId();
    }

    @Override
    public ResourceDetails getResourceById(UUID resourceId) {
        // TODO change the runtime exception to custom exception to return RFC 7807 responses
        return resourceRepository.findResultDetailById(resourceId).orElseThrow(RuntimeException::new);
    }
}
