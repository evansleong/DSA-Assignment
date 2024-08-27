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
        String dmid = dmUI.mgnDonationIDnew();
        Donation donation = dmMap.get(dmid); 
        
        if(donation != null){
            System.out.printf("%-15s %-15s \\n","Donation ID","Donor ID"); 
            System.out.printf("%-15s %-15s \\n",donation.getDonationID(),donation.getdonorID()); 
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            ListInterface<DonationItem> donationItem = donation.getItems();
            if(!donationItem.isEmpty()){
                Iterator<DonationItem> itemIterator = donationItem.iterator();
                while(itemIterator.hasNext()){
                     DonationItem item = itemIterator.next();
                    System.out.printf("%-10s %-10s %-10.2f %-20s\n", 
                                  item.getItemID(), 
                                  item.getItemType(), 
                                  item.getAmount(), 
                                  item.getDescription());
                }
            }            
        }else {
             System.out.println("Error: Donation not found");
        }
    }

    private void amendDonation() {
        // Check if the donation ID exists in the map
        String dmid = dmUI.mgnDonationIDnew();

        if (dmMap.containsKey(dmid)) {
            // Retrieve the donation object
            Donation donation = dmMap.get(dmid);
            System.out.println("Donation Found: " + dmid);

            boolean amending = true;
            while (amending) {
                // Display current donation items
                System.out.println("Current items in this donation:");
                donation.displayItems(); // Assuming listItems() is a method in Donation to list all items

                // Ask user what they want to do: Add, Update, or Remove items
                int amendChoice = dmUI.amendMenu(); // Assume this method provides a menu for amend options

                switch (amendChoice) {
                    case 1: // Add a new item
                        String itemID = generateItemID();
                        int categoryChoice = dmUI.selectDonationCategory();
                        String itemType = "";
                        double amount = 0.0;
                        String description = "";

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
                                description = "Cash Donation";
                                break;
                            default:
                                System.out.println("Invalid category choice. Please try again.");
                                continue;
                        }

                        DonationItem newItem = new DonationItem(itemID, itemType, amount, description);
                        donation.addItem(newItem);
                        System.out.println(itemType + " item added successfully.");
                        break;

                    case 2: // Update an existing item
                        String updateItemID = dmUI.inputItemID(); // Assume this method asks the user to input an item ID
                        DonationItem itemToUpdate = donation.getItemById(updateItemID); // Assume getItemById() fetches an item by its ID

                        if (itemToUpdate != null) {
                            int updateChoice = dmUI.updateItemMenu(); // Assume this method provides options to update item details

                            switch (updateChoice) {
                                case 1: // Update amount
                                    double newAmount = dmUI.mgnQuantity(itemToUpdate.getItemType());
                                    itemToUpdate.setAmount(newAmount);
                                    System.out.println("Amount updated successfully.");
                                    break;
                                case 2: // Update description
                                    String newDescription = dmUI.mgnItemName(itemToUpdate.getItemType());
                                    itemToUpdate.setDescription(newDescription);
                                    System.out.println("Description updated successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid update choice. Please try again.");
                                    continue;
                            }
                        } else {
                            System.out.println("Item ID not found. Please check the ID and try again.");
                        }
                        break;

                    case 3: // Remove an existing item
                        String removeItemID = dmUI.inputItemID();
                        if (donation.removeItemById(removeItemID)) { // Assume removeItemById() removes an item by its ID
                            System.out.println("Item removed successfully.");
                        } else {
                            System.out.println("Item ID not found. Please check the ID and try again.");
                        }
                        break;

                    case 4: // Exit amend menu
                        amending = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }
            }

        System.out.println("Donation amendment completed.");
    } else {
        System.out.println("Donation ID not found in the system. Please check the ID and try again.");
        
    }
          
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
    
        if (dmMap.isEmpty()) {
            System.out.println("No existing donors");
        } else {
            System.out.println("Donor List: \n");
            System.out.printf("%-15s %-20s \t%s\n", "ID", "Donor Name", "Donations");
            System.out.println("---------------------------------------------------------------------------------------------------------");

            Iterator<String> iterator = dmMap.iterator();
            while (iterator.hasNext()) {
                String dnrId = iterator.next();
                Donation donation = dmMap.get(dnrId);
                if (donation != null) {
                    ListInterface<DonationItem> donationItems = donation.getItems();
                    if (!donationItems.isEmpty()) {
                        Iterator<DonationItem> itemIterator = donationItems.iterator();
                        while (itemIterator.hasNext()) {
                            DonationItem item = itemIterator.next();
                            System.out.printf("%-15s %-20s \t%s\n",
                                    donation.getdonorID(),
                                    donation.getDonationID(),
                                    item.toString()
                            );
                        }
                    }
                }
            }
        }
        
//        if (dmMap.isEmpty()) {
//            System.out.println("No existing donors");
//        } else {
//            System.out.println("Donor List: \n");
//            System.out.printf("%-15s %-20s \t%s\n", "ID", "Donor Name", "Donations");
//            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//
//            Iterator<String> iterator = dmMap.iterator();
//            while (iterator.hasNext()) {
//                String dnrId = iterator.next();
//                Donation donation = dmMap.get(dnrId);
//                if (donation != null) {
//                    ListInterface<DonationItem> donationItem = donation.getItems();
//                    String toString = "";
//                    if (!donationItem.isEmpty()) {
//                        Iterator<DonationItem> itemIterator = donationItem.iterator();
//                        while (itemIterator.hasNext()) {
//                            toString += itemIterator.next().toString() + " ";
//                        }
//                    }
//
//                    System.out.printf("%-15s %-20s \t%s\n",
//                            donation.getdonorID(),
//                            donation.getDonationID(),
//                            toString
//                    );
//                }
//            }
//        }
        
        
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
