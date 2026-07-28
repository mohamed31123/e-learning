package com.school.elearning.mapper;


import com.school.elearning.dto.request.LessonRequest;
import com.school.elearning.dto.response.LessonResponse;
import com.school.elearning.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {ProgressMapper.class})
public interface LessonMapper {

    @Mapping(target = "course" , ignore = true)
    @Mapping(target = "id", ignore = true)
    Lesson toEntity(LessonRequest request);

    @Mapping(target = "id", ignore = true)
    void updateLesson(LessonRequest request ,  @MappingTarget Lesson lesson);

    @Mapping(source = "course.id", target = "courseId")

    LessonResponse toResponse(Lesson lesson );
    List<LessonResponse> toResponseList(List<Lesson> lessons);

}
