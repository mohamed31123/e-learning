package com.school.elearning.mapper;

import com.school.elearning.dto.request.CourseRequest;
import com.school.elearning.dto.response.CourseResponse;
import com.school.elearning.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "model.title", target = "modelTitle")
    CourseResponse toResponse(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "model", ignore = true)
    Course toEntity(CourseRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "model", ignore = true)
    void updateCourse(CourseRequest request, @MappingTarget Course course);
}
