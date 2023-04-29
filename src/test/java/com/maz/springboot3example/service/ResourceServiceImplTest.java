package com.maz.springboot3example.service;

import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.domain.ResourceType;
import com.maz.springboot3example.repository.ResourceRepository;
import com.maz.springboot3example.web.mappers.ResourceMapper;
import com.maz.springboot3example.web.model.CreateResourceRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResourceServiceImplTest {

    ResourceService resourceService;
    @Mock
    ResourceRepository resourceRepository;
    @Mock
    ResourceMapper resourceMapper;
    private AutoCloseable closeable;
    private final UUID RESOURCE_ID = UUID.randomUUID();
    private final String RESOURCE_NAME = "TEST_NAME";
    private final Integer RESOURCE_CAPACITY = 100;
    private final String RESOURCE_TYPE = ResourceType.SINGLE.toString();

    private Resource returnedResource;
    private CreateResourceRequest createResourceRequest;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        createResourceRequest = new CreateResourceRequest(RESOURCE_NAME, RESOURCE_CAPACITY, RESOURCE_TYPE);
        resourceService = new ResourceServiceImpl(resourceRepository, resourceMapper);

        returnedResource =
                Resource.builder().id(RESOURCE_ID)
                        .name(RESOURCE_NAME).capacity(RESOURCE_CAPACITY)
                        .type(ResourceType.valueOf(RESOURCE_TYPE)).build();
    }

    @AfterEach
    public void destroy() throws Exception {
        closeable.close();
    }

    @Test
    public void createResourceHappyPathTest() {

        //given
        when(resourceMapper.toDomain(any())).thenReturn(returnedResource);
        when(resourceRepository.saveAndFlush(any())).thenReturn(returnedResource);

        //when
        var expectedURI = resourceService.createResource(createResourceRequest);

        //then
        verify(resourceRepository, times(1)).saveAndFlush(any());
        verify(resourceMapper, times(1)).toDomain(any());
        assertEquals(RESOURCE_ID.toString(), expectedURI.toString());
    }
}
