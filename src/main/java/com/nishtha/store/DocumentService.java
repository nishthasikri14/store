package com.nishtha.store;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final String UPLOAD_DIR = "D:\\uploads\\";
    private DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadDocument(MultipartFile file) throws IOException {
        // Generate unique ID
        String uniqueId = UUID.randomUUID().toString();
        String extension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String uniqueFileName = uniqueId + extension;

        // Save file to disk
        Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
        Files.write(filePath, file.getBytes());

        // Save metadata to database
        Document document = new Document(
                uniqueId,
                file.getOriginalFilename(),
                file.getContentType(),
                filePath.toString()
        );

        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(String id) {
        return documentRepository.findById(id).orElse(null);
    }

    public void deleteDocument(String id) {
        Document document = documentRepository.findById(id).orElse(null);
        if (document != null) {
            // Delete from disk
            try {
                Files.deleteIfExists(Paths.get(document.getFilePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Delete from database
            documentRepository.deleteById(id);
        }
    }
}