package com.maybank.pdf.util.demo.download.history.repository;

import com.maybank.pdf.util.demo.download.history.entity.PDFHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PDFHistoryRepository extends JpaRepository<PDFHistoryEntity, Long>{

    Optional<PDFHistoryEntity> findById (long pdfId);
}