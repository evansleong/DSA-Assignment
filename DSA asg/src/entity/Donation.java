/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Donation {

    private String donationId;
    private String donationDate;
    private String donationType;
    private String donationDetails;
    private List<DonationItem> items;



    public Donation(String donationId, String donationType, String donationDetails, String donationDate) {
        this.donationId = donationId;
        this.donationType = donationType;
        this.donationDetails = donationDetails;
        this.donationDate = donationDate;
//        this.donationDate = formatDate(java.time.LocalDate.now());
    }

    public void addItem(DonationItem item) {
        items.add(item);
    }
//    private String formatDate(LocalDate date) {
//        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-mm-yyyy");
//        return date.format(f);
//    }

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

@Override
    public String toString() {
        return "Donor{" + "donationId=" + donationId + ", donationType=" + donationType + ", donationDetail=" + donationDetails + ", donationDate=" + donationDate + '}';
    }
}
