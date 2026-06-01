package com.thevault.api.brand.service.impl;

import com.thevault.api.brand.dto.BrandReferenceDto;
import com.thevault.api.brand.mapper.BrandMapper;
import com.thevault.api.brand.repository.BrandReferenceRepository;
import com.thevault.api.brand.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandReferenceRepository brandReferenceRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandReferenceDto> getAllActiveBrands() {
        return brandMapper.toDtoList(
                brandReferenceRepository.findAllActive()
        );
    }
}
