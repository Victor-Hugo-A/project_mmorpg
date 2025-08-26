package com.mmorpg.project_pt.repository;

import com.mmorpg.project_pt.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
