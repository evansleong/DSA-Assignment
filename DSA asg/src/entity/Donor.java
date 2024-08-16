package entity;

import java.util.ArrayList;
import java.util.List;

public class Donor {

    private String donorId;
    private String donorName;
    private String donorType;
    private String donorContact;
    private List<Donation> donationsMade;

    public Donor(String donorId, String donorName, String donorType, String donorContact) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.donorType = donorType;
        this.donorContact = donorContact;
        this.donationsMade = new ArrayList<>();
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public void setDonorType(String donorType) {
        this.donorType = donorType;
    }

    public void setDonorContact(String donorContact) {
        this.donorContact = donorContact;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getDonorType() {
        return donorType;
    }

    public String getDonorContact() {
        return donorContact;
    }
    
    public List<Donation> getDonation(){
        return donationsMade;
    }

    public void addDonation(Donation donation) {
        donationsMade.add(donation);
    }

    @Override
    public String toString() {
        return "Donor{" + "donorId=" + donorId + ", donorName=" + donorName
                + ", donorType=" + donorType + ", donorContact=" + donorContact
                + ", donationsMade=" + donationsMade + '}';
    }
}
