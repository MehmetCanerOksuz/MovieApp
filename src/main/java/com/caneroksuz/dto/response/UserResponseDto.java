package com.caneroksuz.dto.response;

import com.caneroksuz.repository.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String name;
    private String surName;
    private String email;
    private String phone;
    private EUserType userType=EUserType.USER;
}
