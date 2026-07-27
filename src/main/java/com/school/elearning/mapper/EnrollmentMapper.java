package com.school.elearning.mapper;


import com.school.elearning.dto.request.EnrollmentRequest;
import com.school.elearning.dto.response.EnrollmentResponse;
import com.school.elearning.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    @Mapping(target = "id" , ignore = true)
    Enrollment toEnrollment(EnrollmentRequest request);

    @Mapping(source = "user.fullName" , target = "userName")
    @Mapping(target = "userId" , ignore = true)
    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);
    List<EnrollmentResponse> toEnrollmentResponseList(List<Enrollment> enrollments);
    @Mapping(target = "id" , ignore = true)
    void updateEnrollment(EnrollmentRequest request ,@MappingTarget Enrollment enrollment);
}
