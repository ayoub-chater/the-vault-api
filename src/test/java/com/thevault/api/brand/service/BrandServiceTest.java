package com.thevault.api.brand.service;

import com.thevault.api.brand.dto.BrandReferenceDto;
import com.thevault.api.brand.entity.BrandReference;
import com.thevault.api.brand.enums.BrandTier;
import com.thevault.api.brand.mapper.BrandMapper;
import com.thevault.api.brand.repository.BrandReferenceRepository;
import com.thevault.api.brand.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock private BrandReferenceRepository brandReferenceRepository;
    @Mock private BrandMapper brandMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void shouldReturnAllActiveBrandsOrdered() {
        List<BrandReference> entities = List.of(
                BrandReference.builder().id(1L).name("Chanel").slug("chanel").tier(BrandTier.LUXURY).build(),
                BrandReference.builder().id(2L).name("Ganni").slug("ganni").tier(BrandTier.CONTEMPORARY).build()
        );

        BrandReferenceDto dto1 = new BrandReferenceDto();
        dto1.setSlug("chanel");
        BrandReferenceDto dto2 = new BrandReferenceDto();
        dto2.setSlug("ganni");

        when(brandReferenceRepository.findAllByIsActiveTrueOrderByDisplayOrderAsc()).thenReturn(entities);
        when(brandMapper.toDtoList(entities)).thenReturn(List.of(dto1, dto2));

        List<BrandReferenceDto> result = brandService.getAllActiveBrands();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getSlug()).isEqualTo("chanel");
    }
}
