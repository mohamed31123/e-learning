package com.school.elearning.mapper;

import com.school.elearning.dto.request.LearningPathRequest;
import com.school.elearning.dto.response.LearningPathResponse;
import com.school.elearning.entity.LearningPath;
import org.apache.catalina.LifecycleState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ModelMapper.class})
public interface LearningPathMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    LearningPath toEntity(LearningPathRequest request);

    LearningPathResponse toResponse(LearningPath learningPath);
    List<LearningPathResponse> toResponseList(List<LearningPath> learningPaths);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void updateLearningPathFromDto(LearningPathRequest request, @MappingTarget LearningPath learningPath);
}
