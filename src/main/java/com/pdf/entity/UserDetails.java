package com.pdf.entity;

public class UserDetails {
	
	@Override
	public String toString() {
		return "UserDetails [lifeAssuredTitle=" + lifeAssuredTitle + ", lifeAssuredFirstName=" + lifeAssuredFirstName
				+ ", lifeAssuredLastName=" + lifeAssuredLastName + ", lifeAssuredDOB=" + lifeAssuredDOB
				+ ", planOption=" + planOption + ", subOption=" + subOption + ", incomeBenefitFrequency="
				+ incomeBenefitFrequency + ", familyIncomeBenefit=" + familyIncomeBenefit + ", premiumPayingTerm="
				+ premiumPayingTerm + ", policyTerm=" + policyTerm + ", premiumPayingMode=" + premiumPayingMode
				+ ", installmentPremium=" + installmentPremium + ", mobile=" + mobile + ", email=" + email
				+ ", distributionChannel=" + distributionChannel + ", category=" + category + ", proposerDifferent="
				+ proposerDifferent + ", proposerTitle=" + proposerTitle + ", proposerFirstName=" + proposerFirstName
				+ ", proposerLastName=" + proposerLastName + ", proposerDOB=" + proposerDOB + "]";
	}


	// Life Assured Details
    private String lifeAssuredTitle;
    private String lifeAssuredFirstName;
    private String lifeAssuredLastName;
    private String lifeAssuredDOB;
    private String planOption;
    private String subOption;
    private String incomeBenefitFrequency;
    private String familyIncomeBenefit;
    private String premiumPayingTerm;
    private String policyTerm;
    private String premiumPayingMode;
    private String installmentPremium;

    // Contact Details
    private String mobile;
    private String email;

    // Distribution and Category
    private String distributionChannel;
    private String category;

    // Proposer Details
    private boolean proposerDifferent;
    private String proposerTitle;
    private String proposerFirstName;
    private String proposerLastName;
    private String proposerDOB;

    
	public UserDetails() {
		super();
	}


	public UserDetails(String lifeAssuredTitle, String lifeAssuredFirstName, String lifeAssuredLastName,
			String lifeAssuredDOB, String planOption, String subOption, String incomeBenefitFrequency,
			String familyIncomeBenefit, String premiumPayingTerm, String policyTerm, String premiumPayingMode,
			String installmentPremium, String mobile, String email, String distributionChannel, String category,
			boolean proposerDifferent, String proposerTitle, String proposerFirstName, String proposerLastName,
			String proposerDOB) {
		super();
		this.lifeAssuredTitle = lifeAssuredTitle;
		this.lifeAssuredFirstName = lifeAssuredFirstName;
		this.lifeAssuredLastName = lifeAssuredLastName;
		this.lifeAssuredDOB = lifeAssuredDOB;
		this.planOption = planOption;
		this.subOption = subOption;
		this.incomeBenefitFrequency = incomeBenefitFrequency;
		this.familyIncomeBenefit = familyIncomeBenefit;
		this.premiumPayingTerm = premiumPayingTerm;
		this.policyTerm = policyTerm;
		this.premiumPayingMode = premiumPayingMode;
		this.installmentPremium = installmentPremium;
		this.mobile = mobile;
		this.email = email;
		this.distributionChannel = distributionChannel;
		this.category = category;
		this.proposerDifferent = proposerDifferent;
		this.proposerTitle = proposerTitle;
		this.proposerFirstName = proposerFirstName;
		this.proposerLastName = proposerLastName;
		this.proposerDOB = proposerDOB;
	}


	public String getLifeAssuredTitle() {
		return lifeAssuredTitle;
	}


	public void setLifeAssuredTitle(String lifeAssuredTitle) {
		this.lifeAssuredTitle = lifeAssuredTitle;
	}


	public String getLifeAssuredFirstName() {
		return lifeAssuredFirstName;
	}


	public void setLifeAssuredFirstName(String lifeAssuredFirstName) {
		this.lifeAssuredFirstName = lifeAssuredFirstName;
	}


	public String getLifeAssuredLastName() {
		return lifeAssuredLastName;
	}


	public void setLifeAssuredLastName(String lifeAssuredLastName) {
		this.lifeAssuredLastName = lifeAssuredLastName;
	}


	public String getLifeAssuredDOB() {
		return lifeAssuredDOB;
	}


	public void setLifeAssuredDOB(String lifeAssuredDOB) {
		this.lifeAssuredDOB = lifeAssuredDOB;
	}


	public String getPlanOption() {
		return planOption;
	}


	public void setPlanOption(String planOption) {
		this.planOption = planOption;
	}


	public String getSubOption() {
		return subOption;
	}


	public void setSubOption(String subOption) {
		this.subOption = subOption;
	}


	public String getIncomeBenefitFrequency() {
		return incomeBenefitFrequency;
	}


	public void setIncomeBenefitFrequency(String incomeBenefitFrequency) {
		this.incomeBenefitFrequency = incomeBenefitFrequency;
	}


	public String getFamilyIncomeBenefit() {
		return familyIncomeBenefit;
	}


	public void setFamilyIncomeBenefit(String familyIncomeBenefit) {
		this.familyIncomeBenefit = familyIncomeBenefit;
	}


	public String getPremiumPayingTerm() {
		return premiumPayingTerm;
	}


	public void setPremiumPayingTerm(String premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}


	public String getPolicyTerm() {
		return policyTerm;
	}


	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}


	public String getPremiumPayingMode() {
		return premiumPayingMode;
	}


	public void setPremiumPayingMode(String premiumPayingMode) {
		this.premiumPayingMode = premiumPayingMode;
	}


	public String getInstallmentPremium() {
		return installmentPremium;
	}


	public void setInstallmentPremium(String installmentPremium) {
		this.installmentPremium = installmentPremium;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDistributionChannel() {
		return distributionChannel;
	}


	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public boolean isProposerDifferent() {
		return proposerDifferent;
	}


	public void setProposerDifferent(boolean proposerDifferent) {
		this.proposerDifferent = proposerDifferent;
	}


	public String getProposerTitle() {
		return proposerTitle;
	}


	public void setProposerTitle(String proposerTitle) {
		this.proposerTitle = proposerTitle;
	}


	public String getProposerFirstName() {
		return proposerFirstName;
	}


	public void setProposerFirstName(String proposerFirstName) {
		this.proposerFirstName = proposerFirstName;
	}


	public String getProposerLastName() {
		return proposerLastName;
	}


	public void setProposerLastName(String proposerLastName) {
		this.proposerLastName = proposerLastName;
	}


	public String getProposerDOB() {
		return proposerDOB;
	}


	public void setProposerDOB(String proposerDOB) {
		this.proposerDOB = proposerDOB;
	}


	


	
    

}