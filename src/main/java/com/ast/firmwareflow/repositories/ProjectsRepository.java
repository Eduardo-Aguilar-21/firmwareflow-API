package com.ast.firmwareflow.repositories;

import com.ast.firmwareflow.models.ProjectsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectsModel, Long> {
}
