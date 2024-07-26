package com.pdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import com.pdf.entity.UserDetails;
import com.pdf.service.PdfStructureService;

@RestController
public class PdfController {

    @Autowired
    private PdfStructureService pdfStructureService;

    @PostMapping("/download-pdf")
    public ResponseEntity<byte[]> pdfConversion(@RequestBody UserDetails userDetails) {
        byte[] pdfData = pdfStructureService.createPdfStructure(userDetails);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}
