package com.thevault.api.consultant.service.impl;

import com.thevault.api.common.exception.ImageConsultantNotFoundException;
import com.thevault.api.consultant.dto.ImageConsultantDto;
import com.thevault.api.consultant.mapper.ImageConsultantMapper;
import com.thevault.api.consultant.repository.ConsultantPortfolioPhotoRepository;
import com.thevault.api.consultant.repository.ImageConsultantRepository;
import com.thevault.api.consultant.service.ImageConsultantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageConsultantServiceImpl implements ImageConsultantService {

    private final ImageConsultantRepository imageConsultantRepository;
    private final ConsultantPortfolioPhotoRepository consultantPortfolioPhotoRepository;
    private final ImageConsultantMapper imageConsultantMapper;

    @Override
    public ImageConsultantDto getConsultantById(Long id) {
        var consultant = imageConsultantRepository.findById(id)
                .orElseThrow(() -> new ImageConsultantNotFoundException(id));

        var dto = imageConsultantMapper.toDto(consultant);
        dto.setPortfolio(imageConsultantMapper.toPortfolioDtoList(
                consultantPortfolioPhotoRepository.findAllByConsultantIdOrderByDisplayOrderAsc(id)
        ));
        return dto;
    }
}
