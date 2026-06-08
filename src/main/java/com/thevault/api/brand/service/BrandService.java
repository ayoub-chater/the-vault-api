package com.thevault.api.brand.service;

import com.thevault.api.brand.dto.BrandReferenceDto;

import java.util.List;

public interface BrandService {

    List<BrandReferenceDto> getAllActiveBrands();
}
