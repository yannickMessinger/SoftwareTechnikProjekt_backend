package de.hsrm.mi.swt02.backend.api.map.repository;

import de.hsrm.mi.swt02.backend.domain.map.GameAsset;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * JPA Respository to manage and persist entitys of Type GameAsset
 */
public interface GameAssetRepository extends JpaRepository<GameAsset, Long> {
}
