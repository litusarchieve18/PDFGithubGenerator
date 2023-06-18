package com.maybank.pdf.util.demo.download.history.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="pdf_history")
@Data
@Builder

public class PDFHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdf_id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Lob
    @Column(name = "downloaded_file")
    private byte[] downloadedFile;

//    @Column(name = "downloaded_file",columnDefinition = "LONGTEXT")
//    private String downloadedFile;
}
