/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Asus
 */
public class Donation {
    private String donationId;
    private String donationDate;
    private String donationType;
    private String donationDetails;
    
    private String distributionId;
    private String distributionDate;
    private String distributionDetails;
    
    private String donorName;
    private String doneeId;

    public Donation(String donationId, String donationType, String donationDetails, String donorName) {
        this.donationId = donationId;
        this.donationType = donationType;
        this.donationDetails = donationDetails;
        this.donorName = donorName;
        this.donationDate = formatDate(java.time.LocalDate.now());
    }
    
    private String formatDate(LocalDate date){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        return date.format(f);
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getDonationDetails() {
        return donationDetails;
    }

    public void setDonationDetails(String donationDetails) {
        this.donationDetails = donationDetails;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public String getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(String distributionDate) {
        this.distributionDate = distributionDate;
    }

    public String getDistributionDetails() {
        return distributionDetails;
    }

    public void setDistributionDetails(String distributionDetails) {
        this.distributionDetails = distributionDetails;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(String doneeId) {
        this.doneeId = doneeId;
    }
    
    
    
    
}
