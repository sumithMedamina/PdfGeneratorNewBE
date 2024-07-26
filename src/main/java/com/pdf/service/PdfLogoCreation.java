package com.pdf.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

@Service
public class PdfLogoCreation {

    public void createLogo(PDDocument document, PDPage page) throws IOException {
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true)) {
            String imagePath = "C:\\Users\\msk27\\Downloads\\logo.png"; // Replace with the path to your logo image
            PDImageXObject logo = PDImageXObject.createFromFile(imagePath, document);
            PDRectangle mediaBox = page.getMediaBox();
            float pageWidth = mediaBox.getWidth();
            float logoWidth = 100; // Desired logo width
            float logoHeight = 50; // Desired logo height
            float logoX = pageWidth - logoWidth - 50; // 50 units margin from the right edge
            float logoY = mediaBox.getHeight() - logoHeight - 20; // Adjust this for vertical positioning

            contentStream.drawImage(logo, logoX, logoY, logoWidth, logoHeight);
        }
    }
}
