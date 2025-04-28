package com.epf.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private final Path imagePath = Paths.get("src/main/webapp/images");

    @GetMapping("/{type}/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String type, @PathVariable String filename) {
        try {
            Path file = imagePath.resolve(type).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                logger.info("Image trouvée : {}", file);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(resource);
            } else {
                logger.warn("Image non trouvée ou non lisible : {}", file);
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            logger.error("Erreur lors de l'accès à l'image : {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
