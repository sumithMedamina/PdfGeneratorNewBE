package com.demo;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class DemoPdf {

    public static void main(String[] args) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                // Add the logo
                String imagePath = "C:\\Users\\msk27\\Downloads\\logo.png"; // Replace with the path to your logo image
                PDImageXObject logo = PDImageXObject.createFromFile(imagePath, document);
                PDRectangle mediaBox = page.getMediaBox();
                float pageWidth = mediaBox.getWidth();
                float logoWidth = 100; // Desired logo width
                float logoHeight = 50; // Desired logo height
                float logoX = pageWidth - logoWidth - 50; // 50 units margin from the right edge
                float logoY = 750; // Adjust this for vertical positioning
                contentStream.drawImage(logo, logoX, logoY, logoWidth, logoHeight);
                 // Adjust position and size as needed

                // First table parameters
                float margin = 50;
                float yStart = 700;
                float tableWidth = 200;
                float yPosition = yStart;
                float rowHeight = 20;
                int rows = 4;
                int cols = 3;
                float colWidth = tableWidth / cols;

                // Draw first table
                drawTable(contentStream, margin, yPosition, rowHeight, tableWidth, rows, cols, colWidth);

                // Draw second table to the right of the first
                float secondTableX = margin + tableWidth + 20; // 20 units space between tables
                drawTable(contentStream, secondTableX, yPosition, rowHeight, tableWidth, rows, cols, colWidth);
            }

            document.save("C:\\Users\\msk27\\Downloads\\TwoTablesSideBySide.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void drawTable(PDPageContentStream contentStream, float x, float y, float rowHeight, float tableWidth,
                                  int rows, int cols, float colWidth) throws IOException {
        // Draw rows
        for (int i = 0; i <= rows; i++) {
            float currentY = y - i * rowHeight;
            contentStream.moveTo(x, currentY);
            contentStream.lineTo(x + tableWidth, currentY);
            contentStream.stroke();
        }

        // Draw columns
        for (int i = 0; i <= cols; i++) {
            float currentX = x + i * colWidth;
            contentStream.moveTo(currentX, y);
            contentStream.lineTo(currentX, y - rowHeight * rows);
            contentStream.stroke();
        }

        // Add text to the cells
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        float cellMargin = 5f;
        float textY = y - 15;
        for (int i = 0; i < rows; i++) {
            float textX = x + cellMargin;
            for (int j = 0; j < cols; j++) {
                contentStream.beginText();
                contentStream.newLineAtOffset(textX, textY);
                contentStream.showText("Data " + (i + 1) + "." + (j + 1));
                contentStream.endText();
                textX += colWidth;
            }
            textY -= rowHeight;
        }
    }
}
