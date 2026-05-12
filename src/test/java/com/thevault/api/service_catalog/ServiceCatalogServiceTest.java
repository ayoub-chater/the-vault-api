package com.thevault.api.service_catalog;

import com.thevault.api.service_catalog.dto.ServiceCatalogDto;
import com.thevault.api.service_catalog.entity.ServiceCatalog;
import com.thevault.api.service_catalog.mapper.ServiceCatalogMapper;
import com.thevault.api.service_catalog.repository.ServiceCatalogRepository;
import com.thevault.api.service_catalog.service.impl.ServiceCatalogServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceCatalogServiceTest {

    @Mock private ServiceCatalogRepository serviceCatalogRepository;
    @Mock private ServiceCatalogMapper serviceCatalogMapper;

    @InjectMocks
    private ServiceCatalogServiceImpl serviceCatalogService;

    @Test
    void shouldReturnAllServicesOrderedByDisplayOrder() {
        List<ServiceCatalog> entities = List.of(
                ServiceCatalog.builder().id(1L).slug("styling-edit").displayOrder(1).build(),
                ServiceCatalog.builder().id(2L).slug("seasonal-wardrobe").displayOrder(2).build()
        );

        ServiceCatalogDto dto1 = new ServiceCatalogDto();
        dto1.setSlug("styling-edit");
        ServiceCatalogDto dto2 = new ServiceCatalogDto();
        dto2.setSlug("seasonal-wardrobe");

        when(serviceCatalogRepository.findAllByOrderByDisplayOrderAsc()).thenReturn(entities);
        when(serviceCatalogMapper.toDtoList(entities)).thenReturn(List.of(dto1, dto2));

        List<ServiceCatalogDto> result = serviceCatalogService.getAllServices();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getSlug()).isEqualTo("styling-edit");
        assertThat(result.get(1).getSlug()).isEqualTo("seasonal-wardrobe");
    }
}
