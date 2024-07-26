package com.pdf.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdf.entity.UserDetails;

@Service
public class UserDetailsTable {

    @Autowired
    private CalculationService calculationService;

    private static final float MARGIN = 50;
    private static final float TABLE_WIDTH = 240; // Adjusted for side-by-side layout
    private static final float CELL_MARGIN = 5;
    private static final float STANDARD_ROW_HEIGHT = 20;
    private static final float EXPANDED_ROW_HEIGHT = 47; // Increased height for the 7th row
    private static final float COLUMN_WIDTH = 120; // Adjusted for side-by-side layout

    public void createUserDetailsTable(PDDocument document, PDPage page, UserDetails userDetails) throws IOException {
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
            float yStart = page.getMediaBox().getHeight() - MARGIN - 50; // Adjust for logo height + margin
            float yPosition = yStart;

            // Draw table headers and content
            String[] headers = {
                "Name of the Prospect/Policyholder:",
                "Age (in years):",
                "Name of the Life Assured:",
                "Age (in years):",
                "Policy Term (in years):",
                "Premium Payment Term (in years):",
                "Amount of Instalment Premium (including Rider Premiums and first year applicable taxes in Rupees):"
            };

            String[] data = {
                userDetails.getLifeAssuredTitle() + " " + userDetails.getLifeAssuredFirstName() + " " + userDetails.getLifeAssuredLastName(),
                String.valueOf(calculationService.calculateAge(userDetails.getLifeAssuredDOB())),
                userDetails.getLifeAssuredTitle() + " " + userDetails.getLifeAssuredFirstName() + " " + userDetails.getLifeAssuredLastName(),
                String.valueOf(calculationService.calculateAge(userDetails.getLifeAssuredDOB())),
                String.valueOf(userDetails.getPolicyTerm()), // Ensure policy term is converted to string
                String.valueOf(userDetails.getPremiumPayingTerm()), // Ensure premium paying term is converted to string
                calculationService.calculatePremiumWithAdditionalPercentage(String.valueOf(userDetails.getInstallmentPremium()))
            };

            drawTable(contentStream, MARGIN, yStart, TABLE_WIDTH, headers, data);
        }
    }

    private void drawTable(PDPageContentStream contentStream, float x, float y, float tableWidth, String[] headers, String[] data) throws IOException {
        float yPosition = y;

        contentStream.setLineWidth(1f);
       

        // Draw table header and data
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10); // Smaller font size for fitting
        for (int i = 0; i < headers.length; i++) {
            float rowHeight = (i == 6) ? EXPANDED_ROW_HEIGHT : STANDARD_ROW_HEIGHT; // Increase height for the 7th row
            drawCell(contentStream, x, yPosition, COLUMN_WIDTH, rowHeight, headers[i], PDType1Font.HELVETICA_BOLD, 10);
            if (i < data.length) {
                drawCell(contentStream, x + COLUMN_WIDTH, yPosition, tableWidth - COLUMN_WIDTH, rowHeight, data[i], PDType1Font.HELVETICA, 10);
            }
            yPosition -= rowHeight;
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

        // Draw text inside cell with wrapping
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
            float rowHeight = (i == 6) ? EXPANDED_ROW_HEIGHT : STANDARD_ROW_HEIGHT; // Adjust height for the 7th row
            contentStream.moveTo(x, yPosition);
            contentStream.lineTo(x + tableWidth, yPosition);
            contentStream.stroke();
            yPosition -= rowHeight;
        }

        // Draw vertical lines
        contentStream.moveTo(x, y);
        contentStream.lineTo(x, y - STANDARD_ROW_HEIGHT * (rowCount - 1) - EXPANDED_ROW_HEIGHT);
        contentStream.stroke();
        contentStream.moveTo(x + tableWidth, y);
        contentStream.lineTo(x + tableWidth, y - STANDARD_ROW_HEIGHT * (rowCount - 1) - EXPANDED_ROW_HEIGHT);
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
