package com.mmorpg.project_pt.repository;

import com.mmorpg.project_pt.domain.Cla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaRepository extends JpaRepository<Cla, Long> {
}