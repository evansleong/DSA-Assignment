/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;
import java.util.Iterator;

/**
 *
 * @author evansleong
 */
public class DonorManagement {

    DonorManagementUI ui = new DonorManagementUI();
    LinkedHashMapInterface<String, Donor> donorMap;

    public DonorManagement() {
        donorMap = new LinkedHashMap<>();
    }

    public void runSystem() {
        boolean running = true;
        while (running) {
            int choice = ui.menuOpt();
            switch (choice) {
                case 1:
                    addDonor();
                    break;
                case 2:
                    removeDonor();
                    break;
                case 3:
                    updateDonor();
                    break;
                case 4:
                    searchDonor();
                    break;
                case 5:
                    listDonorsWithDonations();
                    break;
                case 6:
                    filterDonors();
                    break;
                case 7:
                    generateSummaryReports();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    ui.displayError("Invalid choice. Please try again.");
            }
        }
        ui.close(); // Close the scanner resource when done
    }

    private void addDonor() {
        String id = ui.inputDonorID();
        String name = ui.inputDonorName();
        String type = ui.inputDonorType();
        String contact = ui.inputDonorContact();

        Donor donor = new Donor(id, name, type, contact);
        donorMap.put(id, donor);
        ui.displayMessage("Donor added successfully.");
    }

    private void removeDonor() {
        String id = ui.inputDonorID();
        if (donorMap.get(id) != null) {
            donorMap.remove(id);
            ui.displayMessage("Donor removed successfully.");
        } else {
            ui.displayError("Donor not found.");
        }
    }

    private void updateDonor() {
        String id = ui.updateDonorID();
        Donor donor = donorMap.get(id);
        if (donor != null) {
            String name = ui.updateDonorName();
            String type = ui.updateDonorType();
            String contact = ui.updateDonorContact();

            donor.setDonorName(name);
            donor.setDonorType(type);
            donor.setDonorContact(contact);

            ui.displayMessage("Donor updated successfully.");
        } else {
            ui.displayError("Donor not found.");
        }
    }

    private void searchDonor() {
        String id = ui.searchDonorID();
        Donor donor = donorMap.get(id);
        if (donor != null) {
            ui.displayMessage(donor.toString());
        } else {
            ui.displayError("Donor not found.");
        }
    }

    private void listDonorsWithDonations() {
        // Implement this method based on your requirements
    }

    private void filterDonors() {
        int filterType = ui.inputFilterCriteria();
        // Implement filtering logic based on the criteria
    }

    private void generateSummaryReports() {
        // Implement summary report generation logic
    }
}
