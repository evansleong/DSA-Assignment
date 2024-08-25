/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;

/**
 *
 * @author Asus
 */
public class Donation {

    private String donationId;
    private String donationDate;
    private String donationType;
    private String donationDetails;
    private ListInterface<DonationItem> items;
    private String donorId;

    public Donation(String donationId, String donationType, String donationDetails, String donationDate, String donorId) {
        this.donationId = donationId;
        this.donationType = donationType;
        this.donationDetails = donationDetails;
        this.donationDate = donationDate;
        this.items = new List<>();
        this.donorId = donorId;
    }

    public Donation(String donationId, String donationDate, ListInterface<DonationItem> items, String donorId) {
        this.donationId = donationId;
        this.donationDate = donationDate;
        this.items = items;
        this.donorId = donorId;
    }
    
    public Donation(String donationId, String donationDate, String donorId) {
        this.donationId = donationId;
//        this.donationType = donationType;
//        this.donationDetails = donationDetails;
        this.donationDate = donationDate;
        this.items = new List<>();
        this.donorId = donorId;
    }
    

    public void addItem(DonationItem item) {
        items.add(item);
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

    public String getDonorId() {
        return donorId;
    }

    public void setItems(ListInterface<DonationItem> items) {
        this.items = items;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    
    
    public ListInterface<DonationItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Donation{" + "donationId=" + donationId + ", donationDate=" + donationDate + ", donationType=" + donationType + ", donationDetails=" + donationDetails + ", items=" + items + '}';
    }

}
