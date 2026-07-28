package com.school.elearning.mapper;

import com.school.elearning.dto.response.CertificateResponse;
import com.school.elearning.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "userFullName")
    @Mapping(source = "learningPath.id", target = "learningPathId")
    @Mapping(source = "learningPath.title", target = "learningPathTitle")
    CertificateResponse toResponse(Certificate certificate);

    List<CertificateResponse> toResponseList(List<Certificate> certificates);
}
