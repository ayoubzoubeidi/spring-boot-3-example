package com.maz.springboot3example.service;

import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.domain.ResourceType;
import com.maz.springboot3example.repository.ResourceRepository;
import com.maz.springboot3example.web.exception.NotFoundException;
import com.maz.springboot3example.web.mappers.ResourceMapper;
import com.maz.springboot3example.web.model.CreateResourceRequest;
import com.maz.springboot3example.web.model.UpdateResourceRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResourceServiceTest {

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
    private UpdateResourceRequest updateResourceRequest;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        resourceService = new ResourceServiceImpl(resourceRepository, resourceMapper);
    }

    @AfterEach
    public void destroy() throws Exception {
        closeable.close();
    }

    @Test
    public void createResourceHappyPathTest() {
        //given
        createResourceRequest = new CreateResourceRequest(RESOURCE_NAME, RESOURCE_CAPACITY, RESOURCE_TYPE);
        returnedResource =
                Resource.builder().id(RESOURCE_ID)
                        .name(RESOURCE_NAME).capacity(RESOURCE_CAPACITY)
                        .type(ResourceType.valueOf(RESOURCE_TYPE)).build();
        when(resourceMapper.toDomain(createResourceRequest)).thenReturn(returnedResource);
        when(resourceRepository.saveAndFlush(any())).thenReturn(returnedResource);

        //when
        var returnedId = resourceService.createResource(createResourceRequest);

        //then
        verify(resourceRepository, times(1)).saveAndFlush(any());
        verify(resourceMapper, times(1)).toDomain(createResourceRequest);
        assertEquals(RESOURCE_ID.toString(), returnedId.toString());
    }

    @Test
    public void getResourceByIdReturnsNotFoundException() {
        //given
        when(resourceRepository.existsById(RESOURCE_ID)).thenReturn(false);

        //when
        assertThrows(NotFoundException.class, () -> resourceService.getResourceById(RESOURCE_ID));


        //then
        verify(resourceRepository, times(0)).findResultDetailById(RESOURCE_ID);
    }

    @Test
    public void updateResourceHappyPathTest() {
        //given
        updateResourceRequest = new UpdateResourceRequest(RESOURCE_ID, RESOURCE_NAME, RESOURCE_CAPACITY, RESOURCE_TYPE);
        returnedResource =
                Resource.builder().id(RESOURCE_ID)
                        .name(RESOURCE_NAME).capacity(RESOURCE_CAPACITY)
                        .type(ResourceType.valueOf(RESOURCE_TYPE)).build();
        when(resourceMapper.toDomain(updateResourceRequest)).thenReturn(returnedResource);
        when(resourceRepository.saveAndFlush(any())).thenReturn(returnedResource);
        when(resourceRepository.existsById(RESOURCE_ID)).thenReturn(true);

        //when
        resourceService.updateResourceById(updateResourceRequest);

        //then
        verify(resourceRepository, times(1)).saveAndFlush(any());
        verify(resourceMapper, times(1)).toDomain(updateResourceRequest);
    }

    @Test
    public void updateResourceReturnsNotFoundException() {
        //given
        updateResourceRequest = new UpdateResourceRequest(RESOURCE_ID, RESOURCE_NAME, RESOURCE_CAPACITY, RESOURCE_TYPE);
        when(resourceRepository.existsById(RESOURCE_ID)).thenReturn(false);

        //when
        assertThrows(NotFoundException.class, () -> resourceService.updateResourceById(updateResourceRequest));


        //then
        verify(resourceRepository, times(0)).saveAndFlush(any());
        verify(resourceMapper, times(0)).toDomain(updateResourceRequest);
    }
}
