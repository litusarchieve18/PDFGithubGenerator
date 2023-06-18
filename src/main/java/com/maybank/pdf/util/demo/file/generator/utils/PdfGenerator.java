package com.maybank.pdf.util.demo.file.generator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.maybank.pdf.util.demo.get.user.response.GithubUserResponse;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class PdfGenerator {

    public static byte[] generatePdf5(GithubUserResponse githubUserResponse) throws IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();

        // Add the title of the PDF file
        Paragraph title = new Paragraph("Github User List");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Font font = new Font(Font.FontFamily.HELVETICA, 6);

        // Add the list of GitHub users
        List<GithubUserResponse.Item> items = githubUserResponse.getItems();
        for (GithubUserResponse.Item item : items) {
            Paragraph paragraph = new com.itextpdf.text.Paragraph();

            // Set alignment and font
            paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            paragraph.setFont(font);

            // Add text to the paragraph
            rowGenerator(paragraph, githubUserResponse);

            // Add the paragraph to the document
            document.add(paragraph);
        }

        document.close();

        return outputStream.toByteArray();
    }

    public static byte[] generatePdf(GithubUserResponse githubUserResponse, String outputPath)
            throws FileNotFoundException, DocumentException, IOException {

        // Create a new PDF document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        // Set the PDF writer with the document and output file
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));

        // Open the document
        document.open();

        // Set font settings
        Font font = new Font(Font.FontFamily.HELVETICA, 6);
//        BaseFont baseFont = BaseFont.createFont(StyleConstants.FontConstants.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//        com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 12);

        // Create and add paragraphs for each item in the list
        for (GithubUserResponse.Item item : githubUserResponse.getItems()) {
            // Create a new paragraph
            com.itextpdf.text.Paragraph paragraph = new com.itextpdf.text.Paragraph();

            // Set alignment and font
            paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            paragraph.setFont(font);

            // Add text to the paragraph
            rowGenerator(paragraph, githubUserResponse);

            // Add the paragraph to the document
            document.add(paragraph);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        // Close the document
        document.close();
        writer.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] generatePdf2(GithubUserResponse githubUserResponse, String outputPath)
            throws FileNotFoundException, DocumentException, IOException {

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Create the PDF document
        PdfWriter.getInstance(document, byteArrayOutputStream);

        // Open the document
        document.open();

        // Set font settings
        Font font = new Font(Font.FontFamily.HELVETICA, 6);

        // Create and add paragraphs for each item in the list
        for (GithubUserResponse.Item item : githubUserResponse.getItems()) {
            // Create a new paragraph
            com.itextpdf.text.Paragraph paragraph = new com.itextpdf.text.Paragraph();

            // Set alignment and font
            paragraph.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            paragraph.setFont(font);

            // Add text to the paragraph
            rowGenerator(paragraph, githubUserResponse);

            // Add the paragraph to the document
            document.add(paragraph);
        }
        PdfWriter.getInstance(document, byteArrayOutputStream);

        // Close the document
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private static void rowGenerator(Paragraph paragraph, GithubUserResponse githubUserResponse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(mapper.writeValueAsString(githubUserResponse), new TypeReference<Map<String, Object>>() {});

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            paragraph.add(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }
}

