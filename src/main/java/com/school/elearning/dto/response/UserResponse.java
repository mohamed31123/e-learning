package com.school.elearning.dto.response;

import com.school.elearning.entity.Course;
import com.school.elearning.entity.Enrollment;
import com.school.elearning.enums.UserRole;

import java.util.List;

public record UserResponse(
        Long id ,
        String fullName,
        String email ,
        UserRole role ,
        List<Course> coursesList,
        List<Enrollment> enrollmentList
) {
}
