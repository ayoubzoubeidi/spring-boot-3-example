package com.maz.springboot3example.web.mappers;

import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.web.model.CreateOrUpdateResourceRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ResourceMapper {
    Resource toDomain(CreateOrUpdateResourceRequest createOrUpdateResourceRequest);
}
