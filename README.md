# PDFGithubGenerator

How to Start:
1. Clone project
2. Open your IDE, import dependencies using maven
3. Run this script in mySQL

CREATE DATABASE PDFMAYBANK;

USE PDFMAYBANK;

CREATE TABLE PDF_HISTORY (
pdf_id INT PRIMARY KEY AUTO_INCREMENT,
file_name VARCHAR (255),
created_at datetime DEFAULT current_timestamp,
downloaded_file BLOB
);
4. Update the properties
5. Run the project

# Postman
postman request are available in project's resources
