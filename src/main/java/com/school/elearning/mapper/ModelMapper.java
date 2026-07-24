package com.school.elearning.mapper;

import com.school.elearning.dto.request.ModelRequest;
import com.school.elearning.dto.response.ModelResponse;
import com.school.elearning.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "learningPath", ignore = true)
    Model toEntity(ModelRequest modelRequest);

    @Mapping(source = "learningPath.title", target = "learningPathTitle")
    ModelResponse toResponse(Model model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "learningPath", ignore = true)
    void updateModel(ModelRequest modelRequest, @MappingTarget Model model);
}
