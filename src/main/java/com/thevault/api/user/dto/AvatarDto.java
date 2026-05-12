package com.thevault.api.user.dto;

import lombok.Data;

import java.util.Map;

@Data
public class AvatarDto {

    private Long id;
    private Map<String, Object> styleConfig;
    private Map<String, Object> hairConfig;
    private Map<String, Object> makeupConfig;
    private Map<String, Object> faceConfig;
    private Map<String, Object> bodyConfig;
}
