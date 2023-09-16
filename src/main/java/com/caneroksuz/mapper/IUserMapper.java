package com.caneroksuz.mapper;

import com.caneroksuz.dto.request.RegisterRequestDto;
import com.caneroksuz.dto.response.UserResponseDto;
import com.caneroksuz.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserResponseDto toUserResponseDto(User user);

    User toUser(RegisterRequestDto dto);

    List<UserResponseDto> toUserResponseDtos(List<User> userList);

}
