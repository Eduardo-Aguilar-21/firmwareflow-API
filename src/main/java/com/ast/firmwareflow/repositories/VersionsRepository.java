package com.ast.firmwareflow.repositories;

import com.ast.firmwareflow.models.VersionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionsRepository extends JpaRepository<VersionsModel, Long> {
    List<?> findByProjectModelId(Long projectId);
}
