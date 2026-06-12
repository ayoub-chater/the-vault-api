package com.thevault.api.zebra.dto;

import com.thevault.api.consultant.dto.ImageConsultantDto;
import lombok.Data;

@Data
public class ZebraMatchResultDto {

    private Long consultantId;
    private ImageConsultantDto consultant;
}
