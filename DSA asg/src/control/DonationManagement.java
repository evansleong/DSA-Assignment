/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;

/**
 *
 * @author ASUS
 */
public class DonationManagement {

    DonationManagementUI dmUI = new DonationManagementUI();
    LinkedHashMapInterface<String, Donation> dmMap;

    public DonationManagement() {

        dmMap = new LinkedHashMap<>();
    }

    public void dmstart() {
        boolean running = true;
        while (running) {
            int choice = dmUI.DonateMngMenu();
            switch (choice) {
                case 1:
                    addDonation();
                    break;
                case 2:
                    removeDonation();
                    break;
                case 3:
                    searchDonation();
                    break;
                case 4:
                    amendDonation();
                    break;
                case 5:
                    trackDonation();
                    break;
                case 6:
                    listByDonor();
                    break;
                case 7:
                    listAll();
                    break;
                case 8:
                    filterDonations();
                    break;
                case 9:
                    generateDonationsSummary();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void addDonation() {
        String donorId = dmUI.inputDonorID();
        String dmid = dmUI.mgnDonationIDnew();
        String dmtype = dmUI.mgnDonationtypenew();
        String dmdetails = dmUI.mgnDonationDetailsnew();
        String dmdate = dmUI.mgnDonationDatenew();

        Donation donation = new Donation(dmid, dmtype, dmdetails, dmdate, donorId);
        dmMap.put(dmid, donation);
        System.out.println("Donation successfully added.");

    }

    private void removeDonation() {
        String dmid = dmUI.mgnDonationID();
        if (dmMap.get(dmid) != null) {
            dmMap.remove(dmid);
            System.out.println("Donation succesfully removed");
        } else {
            System.out.println("Donation not found");
        }

    }

    private void searchDonation() {
        String id = dmUI.mgnDonationID();
        Donation donation = dmMap.get(id);
        if (donation != null) {
            System.out.println("Donation Details:");
            System.out.println("ID: " + donation.getDonationId());
            System.out.println("Type: " + donation.getDonationType());
            System.out.println("Detail: " + donation.getDonationDetails());
            System.out.println("Detail: " + donation.getDonationDate());
        } else {
            System.out.println("Donation not found.");
        }

    }

    private void amendDonation() {
        String id = dmUI.mgnDonationID();
        Donation donation = dmMap.get(id);
        if (donation != null) {
            String dmtype = dmUI.mgnDonationtype();
            String dmdetails = dmUI.mgnDonationDetails();
            String dmdate = dmUI.mgnDonationDate();

            donation.setDonationType(dmtype);
            donation.setDonationDetails(dmdetails);
            donation.setDonationDate(dmdate);

            System.out.println("Donor updated successfully.");
        } else {
            System.out.println("Donor not found.");
        }

    }

    private void trackDonation() {

    }

    private void listByDonor() {

    }

    private void listAll() {

    }

    private void filterDonations() {

    }

    private void generateDonationsSummary() {

    }

    public static void main(String[] args) {
        DonationManagement dm = new DonationManagement();
        dm.dmstart();
    }

}
