package com.maybank.pdf.util.demo.file.generator.controller;

import com.maybank.pdf.util.demo.file.generator.service.PDFService;
import com.maybank.pdf.util.demo.get.user.response.GithubUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/pdf")
public class PDFController {

    @Autowired
    public PDFService pdfService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateFile(@RequestParam(defaultValue = "30") Integer itemPerPage) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + SAMPLE_PDF);

        return new ResponseEntity<>(pdfService.generatePDF(itemPerPage, "asc"),
                headers,
                HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> getPDFFileByID(@RequestParam long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return new ResponseEntity<>(pdfService.getPDFFileByID(id).getDownloadedFile(),
                headers,
                HttpStatus.OK);
    }



}
