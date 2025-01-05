package com.lcaohoanq.demo.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileUploadTest {

    private String fileBeingUpload = "src/main/resources/main.c";
    private String uploadedFile = "uploads/22dc18c8-8a49-4af7-bfda-44653f736656_main.c";
    private String uploadedImageFile = """
        uploads/2f281076-ee0d-45e0-a071-d8b74a725ab3_Screenshot from 2025-01-04 16-02-38.png
        """ ;
    
    @Test
    public void testFileBeingUploadAndSavedDoesEqual() throws IOException {
        assertEquals(
            Files.readString(Path.of(fileBeingUpload)),
            Files.readString(Path.of(uploadedFile))
        );
    }
    
//    @Test
//    public void testFileBeingUploadAndSavedDoesNotEqual() throws IOException {
//        assertNotEquals(
//            Files.readString(Path.of(fileBeingUpload)),
//            Files.readString(Path.of(uploadedImageFile))
//        );
//    }
    
    @Test
    public void testNoResourceThrowsException() {
        assertThrows(IOException.class, () -> {
            Files.readString(Path.of("uploads/hehe.cpp"));
        });
    }
}
