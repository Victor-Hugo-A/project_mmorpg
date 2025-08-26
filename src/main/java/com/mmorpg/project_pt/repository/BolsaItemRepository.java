package com.mmorpg.project_pt.repository;

import com.mmorpg.project_pt.domain.BolsaItem;
import com.mmorpg.project_pt.domain.BolsaItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BolsaItemRepository extends JpaRepository<BolsaItem, BolsaItemId> {
}