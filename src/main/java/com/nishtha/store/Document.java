package com.nishtha.store;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    private String id;

    private String fileName;
    private String contentType;
    private String filePath;
    private LocalDateTime uploadDate;

    public Document() {}

    public Document(String id, String fileName, String contentType, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.filePath = filePath;
        this.uploadDate = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getFileName() { return fileName; }
    public String getContentType() { return contentType; }
    public String getFilePath() { return filePath; }
    public LocalDateTime getUploadDate() { return uploadDate; }
}