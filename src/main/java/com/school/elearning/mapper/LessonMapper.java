package com.school.elearning.mapper;


import com.school.elearning.dto.request.LessonRequest;
import com.school.elearning.dto.response.LessonResponse;
import com.school.elearning.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "course" , ignore = true)
    @Mapping(target = "id", ignore = true)
    Lesson toEntity(LessonRequest request);

    @Mapping(target = "id", ignore = true)
    void updateLesson(LessonRequest request);

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(target = "course" , ignore = true)
    LessonResponse toResponse(LessonRequest request , @MappingTarget Lesson lesson);

}
