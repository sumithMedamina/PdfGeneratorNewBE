package com.pdf.service;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {
	
	// Method to calculate age based on DOB (Year only for simplicity)
    public String calculateAge(String dob) {
        // Assume dob format is "yyyy-MM-dd"
        int birthYear = Integer.parseInt(dob.split("-")[0]);
        int currentYear = java.time.Year.now().getValue();
        return String.valueOf(currentYear - birthYear);
    }
    
    public String calculatePremiumWithAdditionalPercentage(String premium) {
        int prem = Integer.parseInt(premium);
        double increasedPremium = prem * 1.045; // Adding 4.5%
        return String.valueOf((int) Math.round(increasedPremium)); // Return as string
    }


}
