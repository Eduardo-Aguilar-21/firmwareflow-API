package com.ast.firmwareflow.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DirectoryService {
    private final String basePath = "src/main/java/com/ast/firmwareflow/files";

    public void createDirectoryWithName(String directoryName) {
        String fullDirectoryPath = basePath + File.separator + directoryName;

        File directory = new File(fullDirectoryPath);
        if (!directory.exists()) {
            boolean wasSuccessful = directory.mkdirs();
            if (wasSuccessful) {
                System.out.println("Directorio creado exitosamente en " + fullDirectoryPath);
            } else {
                System.out.println("No se pudo crear el directorio en " + fullDirectoryPath);
            }
        } else {
            System.out.println("El directorio ya existe en " + fullDirectoryPath);
        }
    }
}
