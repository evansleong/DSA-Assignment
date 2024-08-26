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
 * @author ASUS
 */
public class DonationManagement {

    DonationManagementUI dmUI = new DonationManagementUI();
    LinkedHashMapInterface<String, Donation> dmMap;
    private int donationCounter = 1; // Counter for generating unique IDs
    private int itemCounter = 0;
    private DonorManagement donorManagement;

    public DonationManagement(DonorManagement donorManagement) {
        dmMap = new LinkedHashMap<>();
        this.donorManagement = donorManagement;
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

    private String generateDonationID() {
        return "DON" + String.format("%04d", donationCounter++); // e.g., DON0001, DON0002
    }

    private String generateItemID() {
        return "ITEM" + String.format("%04d", ++itemCounter);
    }

    private void addDonation() {
        // Input donor ID
        String donorId = dmUI.inputDonorID();

        // Check if the donor ID exists in the system
        if (donorManagement.donorIdExists(donorId)) {
            // Retrieve the donor's name
            String donorName = donorManagement.getDonorName(donorId);
            System.out.println("Donor Found: " + donorName);

            // Generate new donation ID
            String donationID = generateDonationID();
            System.out.println("Generated Donation ID: " + donationID);

            // Create a new donation with the generated ID and donor ID
            Donation donation = new Donation(donationID, donorId, new List<DonationItem>());

            boolean addingItems = true;
            while (addingItems) {
                // Select category and get item details
                int categoryChoice = dmUI.selectDonationCategory();
                String itemType = "";
                double amount = 0.0;
                String description = "";
                String itemID = generateItemID(); // Generate item ID

                // Create a new DonationItem based on category choice
                switch (categoryChoice) {
                    case 1: // Food
                        itemType = "Food";
                        description = dmUI.mgnItemName("Food");
                        amount = dmUI.mgnQuantity("Food");
                        break;
                    case 2: // Daily Necessities
                        itemType = "Kind";
                        description = dmUI.mgnItemName("Daily Necessities");
                        amount = dmUI.mgnQuantity("Daily Necessities");
                        break;
                    case 3: // Cash
                        itemType = "Cash";
                        amount = dmUI.mgnCashAmount();
                        description = "Cash Donation"; // Description for cash donation
                        break;
                    default:
                        System.out.println("Invalid category choice. Please try again.");
                        continue;
                }

                // Create the DonationItem
                DonationItem item = new DonationItem(itemID, itemType, amount, description);

                // Add item to the donation
                donation.addItem(item);
                System.out.println(itemType + " item added successfully.");

                // Check if the user wants to add more items
                addingItems = dmUI.askAddMoreItems();
            }

            // Put the donation into the map
            dmMap.put(donationID, donation);
            System.out.println("Donation successfully added.");
        } else {
            System.out.println("Donor ID not found in the system. Please check the ID and try again.");
        }
    }

    private void removeDonation() {
        String dmid = dmUI.mgnDonationIDnew();
        if (dmMap.get(dmid) != null) {
            dmMap.remove(dmid);
            System.out.println("Donation successfully removed.");
        } else {
            System.out.println("Donation not found.");
        }
    }

    private void searchDonation() {

    }

    private void amendDonation() {
//        String id = dmUI.mgnDonationIDnew();
//        Donation donation = dmMap.get(id);
//        if (donation != null) {
//            String dmtype = dmUI.mgnDonationtype();
//            String dmdetails = dmUI.mgnDonationDetails();
//            String dmdate = dmUI.mgnDonationDate();
//
//            donation.setDonationType(dmtype);
//            donation.setDonationDetails(dmdetails);
//            donation.setDonationDate(dmdate);
//
//            System.out.println("Donation updated successfully.");
//        } else {
//            System.out.println("Donation not found.");
//        }
    }

    private void trackDonation() {
        // Implement tracking logic here
    }

    private void listByDonor() {
    }

    private void listAll() {
        if (dmMap.isEmpty()) {
            System.out.println("No donations available.");
            return;
        }

        // Print the header
        System.out.printf("%-15s %-25s %s%n", "DONATION ID", "DONOR NAME", "DONATION ITEMS");
        System.out.println("---------------------------------------------------------------");

        // Create an iterator for the LinkedHashMap
        Iterator<String> mapIterator = dmMap.iterator();

        // Iterate through all entries in the LinkedHashMap
        while (mapIterator.hasNext()) {
            String donationID = mapIterator.next();
            Donation donation = dmMap.get(donationID);

            if (donation != null) {
                String donorID = donation.getdonorID();
                String donorName = donorManagement.getDonorName(donorID);

                // Build donation items string
                StringBuilder itemsBuilder = new StringBuilder();
                Iterator<DonationItem> itemIterator = donation.getItems().iterator();
                while (itemIterator.hasNext()) {
                    DonationItem item = itemIterator.next();
                    itemsBuilder.append(String.format("  %-15s x %.2f%n", item.getItemType(), item.getAmount()));
                }

                // Print donation details
                System.out.printf("%-15s %-25s %s%n",
                        donation.getDonationID(),
                        donorName,
                        itemsBuilder.toString().trim()
                );
                System.out.println("---------------------------------------------------------------");
            }
        }
    }

    private void filterDonations() {
        // Implement filtering logic here
    }

    private void generateDonationsSummary() {
        // Implement summary generation logic here
    }

}
