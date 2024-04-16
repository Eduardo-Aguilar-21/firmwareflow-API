package com.ast.firmwareflow.services;

import com.ast.firmwareflow.models.VersionsModel;
import com.ast.firmwareflow.repositories.VersionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VersionsService {
    @Autowired
    private VersionsRepository versionsRepository;

    public List<VersionsModel> findAll() {
        return versionsRepository.findAll();
    }

    public Optional<VersionsModel> findById(Long id) {
        return versionsRepository.findById(id);
    }

    public VersionsModel createVersion(VersionsModel version) {
        return versionsRepository.save(version);
    }

    public VersionsModel updateVersion(Long id, VersionsModel updatedVersion) {
        if (versionsRepository.existsById(id)) {
            updatedVersion.setId(id);
            return versionsRepository.save(updatedVersion);
        } else {
            return null; // or handle error as needed
        }
    }

    public void deleteVersion(Long id) {
        versionsRepository.deleteById(id);
    }

    public List<?> findByProjectModelId(Long projectId) {
        return versionsRepository.findByProjectModelId(projectId);
    }
}
