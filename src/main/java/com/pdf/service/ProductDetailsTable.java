package com.pdf.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsTable {

    private static final float MARGIN = 50;
    private static final float TABLE_WIDTH = 240; // Adjusted for side-by-side layout
    private static final float CELL_MARGIN = 5;
    private static final float ROW_HEIGHT = 20;
    private static final float COLUMN_WIDTH = 120; // Adjusted for side-by-side layout

    public void createProductDetailsTable(PDDocument document, PDPage page) throws IOException {
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
            float yStart = page.getMediaBox().getHeight() - MARGIN - 50; // Adjust for logo height + margin
            float yPosition = yStart;

            // Product details
            String[] headers = {
                "Name of the Product:",
                "Tag Line:",
                "Unique Identification No.:",
                "GST Rate - Base Product (first year):",
                "GST Rate - Base Product (second year onwards):"
            };

            String[] data = {
                "ET Life - Premier Guaranteed Income",
                "An Individual, Non-Linked, Non-Participating Savings Life Insurance Product",
                "147N072V03",
                "4.50%",
                "2.25%"
            };

            drawTable(contentStream, MARGIN + 260, yPosition, TABLE_WIDTH, headers, data); // Shifted to the right
        }
    }

    private void drawTable(PDPageContentStream contentStream, float x, float y, float tableWidth, String[] headers, String[] data) throws IOException {
        float yPosition = y;

        contentStream.setLineWidth(1f);
        

        // Draw table header and data
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10); // Smaller font size for fitting
        for (int i = 0; i < headers.length; i++) {
            drawCell(contentStream, x, yPosition, COLUMN_WIDTH, ROW_HEIGHT, headers[i], PDType1Font.HELVETICA_BOLD, 10);
            drawCell(contentStream, x + COLUMN_WIDTH, yPosition, tableWidth - COLUMN_WIDTH, ROW_HEIGHT, data[i], PDType1Font.HELVETICA, 10);
            yPosition -= ROW_HEIGHT;
        }

        // Draw table borders
        drawTableBorders(contentStream, x, y, tableWidth, headers.length);
    }

    private void drawCell(PDPageContentStream contentStream, float x, float y, float width, float height, String text, PDType1Font font, float fontSize) throws IOException {
        contentStream.setFont(font, fontSize);

        // Draw cell borders
        contentStream.moveTo(x, y);
        contentStream.lineTo(x + width, y);
        contentStream.lineTo(x + width, y - height);
        contentStream.lineTo(x, y - height);
        contentStream.lineTo(x, y);
        contentStream.stroke();

        // Draw text inside cell
        String[] lines = wrapText(text, width - 2 * CELL_MARGIN, font, fontSize);
        float textY = y - CELL_MARGIN - fontSize;
        for (String line : lines) {
            contentStream.beginText();
            contentStream.newLineAtOffset(x + CELL_MARGIN, textY);
            contentStream.showText(line);
            contentStream.endText();
            textY -= fontSize + 2; // Move to next line, considering line spacing
        }
    }

    private void drawTableBorders(PDPageContentStream contentStream, float x, float y, float tableWidth, int rowCount) throws IOException {
        float yPosition = y;
        for (int i = 0; i <= rowCount; i++) {
            contentStream.moveTo(x, yPosition);
            contentStream.lineTo(x + tableWidth, yPosition);
            contentStream.stroke();
            yPosition -= ROW_HEIGHT;
        }

        // Draw vertical lines
        contentStream.moveTo(x, y);
        contentStream.lineTo(x, y - ROW_HEIGHT * rowCount);
        contentStream.stroke();
        contentStream.moveTo(x + tableWidth, y);
        contentStream.lineTo(x + tableWidth, y - ROW_HEIGHT * rowCount);
        contentStream.stroke();
    }

    private String[] wrapText(String text, float maxWidth, PDType1Font font, float fontSize) throws IOException {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        float lineWidth = 0;
        StringBuilder wrappedText = new StringBuilder();

        for (String word : words) {
            float wordWidth = font.getStringWidth(word + " ") / 1000 * fontSize;
            if (lineWidth + wordWidth > maxWidth) {
                wrappedText.append(line.toString().trim()).append("\n");
                line = new StringBuilder();
                lineWidth = 0;
            }
            line.append(word).append(" ");
            lineWidth += wordWidth;
        }

        if (line.length() > 0) {
            wrappedText.append(line.toString().trim());
        }

        return wrappedText.toString().split("\n");
    }
}
