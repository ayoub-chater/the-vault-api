package com.thevault.api.zebra.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ZebraSessionDto {

    private Long id;
    private Long userId;
    private String status;
    private Long matchedConsultantId;
    private Long styleProfileRef;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}
