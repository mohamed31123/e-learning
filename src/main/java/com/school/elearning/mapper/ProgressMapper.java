package com.school.elearning.mapper;

import com.school.elearning.dto.request.ProgressRequest;
import com.school.elearning.dto.response.ProgressReponse;
import com.school.elearning.entity.Progress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProgressMapper {
    @Mapping(target = "id" , ignore = true)
    Progress toProgressEntity(ProgressRequest progressRequest);
    @Mapping(target = "lessonTitle" , source = "lesson.title")
    @Mapping(target = "lesson" , ignore = true)
    @Mapping(target = "enrollment"  , ignore = true)
    ProgressReponse toProgressReponse(Progress progress);

    @Mapping(target = "id" , ignore = true)
    void updateProgress(ProgressRequest progressRequest,@MappingTarget Progress progress);
}
