package com.hr.system.mapper;

import com.hr.system.dto.request.LeaveCreateRequest;
import com.hr.system.dto.response.LeaveInfoResponse;
import com.hr.system.model.Leave;
import com.hr.system.model.enums.LeaveStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {LeaveStatus.class, LocalDateTime.class})
public interface LeaveMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "status", expression = "java(LeaveStatus.PENDING)")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Leave toEntity(LeaveCreateRequest request, UUID employeeId);

    LeaveInfoResponse toResponse(Leave leave);
}
