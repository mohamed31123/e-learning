package com.school.elearning.mapper;

import com.school.elearning.dto.UserRequest;
import com.school.elearning.dto.UserResponse;
import com.school.elearning.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    void updateUser(UserRequest userRequest);

}
