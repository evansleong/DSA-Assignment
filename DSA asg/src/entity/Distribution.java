/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Asus
 */
public class Distribution {
    private String distributionId;
    private String distributionDate;
    private String distributionDetails;
    private String doneeId;

    public Distribution(String distributionId, String distributionDate, String distributionDetails, String doneeId) {
        this.distributionId = distributionId;
        this.distributionDate = distributionDate;
        this.distributionDetails = distributionDetails;
        this.doneeId = doneeId;
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

    public String getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(String doneeId) {
        this.doneeId = doneeId;
    }
    
    
}
