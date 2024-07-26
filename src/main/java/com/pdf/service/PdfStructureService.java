package com.pdf.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdf.entity.UserDetails;

@Service
public class PdfStructureService {

    @Autowired
    private PdfLogoCreation logoCreation;

    @Autowired
    private UserDetailsTable userDetailsTable;
    
    @Autowired
    private ProductDetailsTable productDetailsTable;

    public byte[] createPdfStructure(UserDetails userDetails) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Create logo
            logoCreation.createLogo(document, page);

            // Create user details table
            userDetailsTable.createUserDetailsTable(document, page, userDetails);
            
            // create product details table
            productDetailsTable.createProductDetailsTable(document, page);

            // Save the document to a byte array output stream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0]; // Return empty array in case of an error
        }
    }
}
