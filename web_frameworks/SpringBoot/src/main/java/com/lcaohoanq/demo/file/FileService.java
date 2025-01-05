package com.lcaohoanq.demo.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(MultipartFile multipartFile) throws IOException {
        // Generate a unique file name
        String uniqueFileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        // Define the directory where files will be stored (uploads folder)
        Path uploadsDir = Paths.get("uploads/");

        // Create the directory if it does not exist
        if (Files.notExists(uploadsDir)) {
            Files.createDirectories(uploadsDir);
        }

        // Define the full path where the file will be saved
        Path filePath = uploadsDir.resolve(uniqueFileName);

        // Write the file content to the path
        Files.write(filePath, multipartFile.getBytes(), StandardOpenOption.CREATE);

        // Persist file metadata to the database
        File file = File.builder()
            .fileName(uniqueFileName)
            .fileType(multipartFile.getContentType())
            .fileSize(multipartFile.getSize())
            .fileContent(filePath.toString())  // Save the file path instead of content
            .build();

        return fileRepository.save(file);
    }
}
