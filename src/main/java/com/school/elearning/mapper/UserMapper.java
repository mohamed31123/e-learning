package com.school.elearning.mapper;

import com.school.elearning.dto.request.UserRequest;
import com.school.elearning.dto.response.UserResponse;
import com.school.elearning.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    void updateUser(UserRequest userRequest, @MappingTarget User user);

}
