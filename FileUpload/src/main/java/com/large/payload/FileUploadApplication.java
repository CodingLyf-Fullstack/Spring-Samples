package com.large.payload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;



@SpringBootApplication
@RestController
@ControllerAdvice
public class FileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }

    // Upload endpoint
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        double sizeInMB = file.getSize() / (1024.0 * 1024.0); 
        String formattedSize = String.format("%.2f", sizeInMB); // Format to 2 decimal places
        return ResponseEntity.ok("Uploaded " + fileName + " (" + formattedSize + " MB) successfully.");
    }

    // Handle max size exception
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleSizeLimit(MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body("File too large! Max allowed size is 2MB.");
    }

}
