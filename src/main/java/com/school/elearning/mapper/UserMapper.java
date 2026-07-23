package com.school.elearning.mapper;

import com.school.elearning.dto.UserRequest;
import com.school.elearning.dto.UserResponse;
import com.school.elearning.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequest toEntity(UserRequest userRequest);

    User toResponse(UserResponse userResponse);

    void updateUser(UserRequest userRequest);

}
