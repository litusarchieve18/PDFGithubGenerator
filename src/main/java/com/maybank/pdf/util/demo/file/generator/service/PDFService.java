package com.maybank.pdf.util.demo.file.generator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itextpdf.text.DocumentException;
import com.maybank.pdf.util.demo.download.history.entity.PDFHistoryEntity;
import com.maybank.pdf.util.demo.download.history.repository.PDFHistoryRepository;
import com.maybank.pdf.util.demo.file.generator.utils.PdfGenerator;
import com.maybank.pdf.util.demo.get.user.response.GithubUserResponse;
import com.maybank.pdf.util.demo.get.user.service.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PDFService {

    @Autowired
    private GithubService githubService;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    PDFHistoryRepository pdfHistoryRepository;

    private static final String filename= "GithubUser";

    public byte[] generatePDF(Integer perPage, String sort) throws JsonProcessingException, DocumentException {
        GithubUserResponse githubUserResponse = githubService.getListUser(perPage, sort);
        log.info("githubUserResponse {}", githubUserResponse );
        try {
            byte[] file = pdfGenerator.generatePdf5(githubUserResponse);
            LocalDateTime localDateTime = LocalDateTime.now();
            pdfHistoryRepository.save(PDFHistoryEntity.builder()
                            .fileName(filename + localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                            .createdAt(localDateTime)
                            .downloadedFile(file)
                    .build());
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PDFHistoryEntity> getListPdf() {
        return pdfHistoryRepository.findAll();
    }

    public PDFHistoryEntity getPDFFileByID(long idHistory) {
        Optional<PDFHistoryEntity> pdfHistory = pdfHistoryRepository.findById(idHistory);
        return pdfHistory.orElseThrow(() -> new EmptyResultDataAccessException("History not found", 1));
    }



}
