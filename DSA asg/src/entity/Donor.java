package entity;

import adt.*;

/**
 *
 * @author laixianyu
 */
public class Donor {

    private String idHead = "DNR-";
    private static int idNo = 001;
    private String donorId;
    private String donorName;
    private String donorType;
    private String donorContact;
    private ListInterface<Donation> donations;

    public Donor(String donorName, String donorType, String donorContact) {
        this.donorId = idHead + String.format("%03d", idNo++);
        this.donorName = donorName;
        this.donorType = donorType;
        this.donorContact = donorContact;
        this.donations = new List<>();
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

    public ListInterface<Donation> getDonation() {
        return donations;
    }

    public void addDonation(Donation donation) {
        this.donations.add(donation);
    }

    @Override
    public String toString() {
        return "Donor{" + "donorId=" + donorId + ", donorName=" + donorName
                + ", donorType=" + donorType + ", donorContact=" + donorContact
                + ", donationsMade=" + donations + '}';
    }
}
