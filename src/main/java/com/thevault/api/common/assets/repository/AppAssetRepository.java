package com.thevault.api.common.assets.repository;

import com.thevault.api.common.assets.entity.AppAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppAssetRepository extends JpaRepository<AppAsset, Long> {

    Optional<AppAsset> findByKey(String key);

    List<AppAsset> findByKeyStartingWith(String prefix);
}
