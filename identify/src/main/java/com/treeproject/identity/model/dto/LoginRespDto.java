package com.treeproject.identity.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespDto {
    private String token;
    private Map<String, Object> permissions;
}
