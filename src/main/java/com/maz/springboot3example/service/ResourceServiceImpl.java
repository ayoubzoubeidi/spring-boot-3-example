package com.maz.springboot3example.service;

import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.repository.ResourceRepository;
import com.maz.springboot3example.web.mappers.ResourceMapper;
import com.maz.springboot3example.web.model.CreateResourceRequest;
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
}
