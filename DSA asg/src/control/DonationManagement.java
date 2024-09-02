/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import dao.DonationDataSeeder;
import dao.DoneeDataSeeder;
import entity.*;
import java.util.Iterator;
import utility.ConsoleUtils;

/**
 *
 * @author LEEYIHANG
 */
public class DonationManagement {

    DonationManagementUI dmUI = new DonationManagementUI();
    MapInterface<String, Donation> dmMap;
    private int donationCounter = 1; 
    private int itemCounter = 0;
    private final DonorManagement donorManagement;
    private final DonationDataSeeder dataSeeder;

    public DonationManagement(DonorManagement donorManagement) {
        dmMap = new LinkedHashMap<>();
        this.donorManagement = donorManagement;
        dataSeeder = new DonationDataSeeder();
        initializeDonationMap();

    }

    private void initializeDonationMap() {
        this.dmMap = dataSeeder.getDonationMap();
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
        return "DON-" + String.format("%04d", donationCounter++); 
    }

    private String generateItemID() {
        return "ITEM" + String.format("%04d", ++itemCounter);
    }

    private void addDonation() {

        String donorId = dmUI.inputDonorID();

       
        if (donorManagement.donorIdExists(donorId)) {
            
            String donorName = donorManagement.getDonorName(donorId);
            System.out.println("Donor Found: " + donorName);

            String donationID = generateDonationID();
            System.out.println("Generated Donation ID: " + donationID);

            Donation donation = new Donation(donationID, donorId, new List<DonationItem>());

            boolean addingItems = true;
            while (addingItems) {
              
                int categoryChoice = dmUI.selectDonationCategory();
                String itemType = "";
                double amount = 0.0;
                String description = "";
                String itemID = generateItemID(); 

          
                switch (categoryChoice) {
                    case 1: 
                        itemType = "Food";
                        description = dmUI.mgnItemName("Food");
                        amount = dmUI.mgnQuantity("Food");
                        break;
                    case 2: 
                        itemType = "Daily Necessities";
                        description = dmUI.mgnItemName("Daily Necessities");
                        amount = dmUI.mgnQuantity("Daily Necessities");
                        break;
                    case 3: 
                        itemType = "Cash";
                        amount = dmUI.mgnCashAmount();
                        description = "Cash Donation"; 
                        break;
                    default:
                        System.out.println("Invalid category choice. Please try again.");
                        continue;
                }

                
                DonationItem item = new DonationItem(itemID, itemType, amount, description);

                
                donation.addItem(item);
                System.out.println(itemType + " item added successfully.");

             
                addingItems = dmUI.askAddMoreItems();
            }

         
            dmMap.put(donationID, donation);
            System.out.println("Donation successfully added.");
        } else {
            System.out.println("Donor ID not found in the system. Please check the ID and try again.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
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

        if (donation != null) {
            System.out.println("--------------------------------------------------------------------");
            System.out.printf("%-15s %-15s \n", "Donation ID", "Donor ID");
            System.out.printf("%-15s %-15s \n", donation.getDonationID(), donation.getdonorID());
            System.out.println("--------------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-20s\n", "Item ID", "TYPE", "AMOUNT", "DESC");
            System.out.println("--------------------------------------------------------------------");
            ListInterface<DonationItem> donationItem = donation.getItems();
            if (!donationItem.isEmpty()) {
                Iterator<DonationItem> itemIterator = donationItem.iterator();
                while (itemIterator.hasNext()) {
                    DonationItem item = itemIterator.next();
                    System.out.printf("%-10s %-20s %-10.2f %-20s\n",
                            item.getItemID(),
                            item.getItemType(),
                            item.getAmount(),
                            item.getDescription());
                }
            }
        } else {
            System.out.println("Error: Donation not found");
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void amendDonation() {
       
        String dmid = dmUI.mgnDonationIDnew();

        if (dmMap.containsKey(dmid)) {
           
            Donation donation = dmMap.get(dmid);
            System.out.println("Donation Found: " + dmid);

            boolean amending = true;
            while (amending) {
                
                System.out.println("Current items in this donation:");
                
                System.out.println(donation.displayItems(donation));
               
                int amendChoice = dmUI.amendMenu(); 

                switch (amendChoice) {
                    case 1: 
                        String itemID = generateItemID();
                        int categoryChoice = dmUI.selectDonationCategory();
                        String itemType = "";
                        double amount = 0.0;
                        String description = "";

                        switch (categoryChoice) {
                            case 1: 
                                itemType = "Food";
                                description = dmUI.mgnItemName("Food");
                                amount = dmUI.mgnQuantity("Food");
                                break;
                            case 2: 
                                itemType = "Kind";
                                description = dmUI.mgnItemName("Daily Necessities");
                                amount = dmUI.mgnQuantity("Daily Necessities");
                                break;
                            case 3: 
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

                    case 2: 
                        String updateItemID = dmUI.inputItemID(); 
                        DonationItem itemToUpdate = donation.getItemById(updateItemID);

                        if (itemToUpdate != null) {
                            int updateChoice = dmUI.updateItemMenu();

                            switch (updateChoice) {
                                case 1: 
                                    double newAmount = dmUI.mgnQuantity(itemToUpdate.getItemType());
                                    itemToUpdate.setAmount(newAmount);
                                    System.out.println("Amount updated successfully.");
                                    break;
                                case 2: 
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


                    case 3: 
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
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void trackDonation() {
      
        int choice = dmUI.selectDonationCategory();

       
        String selectedCategory = "";
        switch (choice) {
            case 1:
                selectedCategory = "Food";
                break;
            case 2:
                selectedCategory = "Cash";
                break;
            case 3:
                selectedCategory = "Daily Necessities";
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }

        if (dmMap.isEmpty()) {
            System.out.println("No existing donors");
        } else {
            System.out.println("Donation List (Category: " + selectedCategory + "):\n");
            System.out.println("---------------------------------------------------------------------------------------------------------");

            Iterator<String> iterator = dmMap.iterator();
            while (iterator.hasNext()) {
                String dnrId = iterator.next();
                Donation donation = dmMap.get(dnrId);
                if (donation != null) {
                    ListInterface<DonationItem> donationItems = donation.getItems();

                    if (!donationItems.isEmpty()) {
                     
                        Iterator<DonationItem> itemIterator = donationItems.iterator();
                        boolean hasItemsInCategory = false;
                        while (itemIterator.hasNext()) {
                            DonationItem item = itemIterator.next();
                            if (item.getItemType().equals(selectedCategory)) {
                                if (!hasItemsInCategory) {
                                    System.out.printf("Donor ID: %-15s Donor Name: %-20s %n", donation.getdonorID(), donation.getDonationID());
                                    System.out.println("---------------------------------------------------------------------------------------------------------");
                                    hasItemsInCategory = true;
                                }
                                System.out.printf("%-15s %-20s %-25s %-10.2f %n", item.getItemID(), item.getItemType(), item.getDescription(), item.getAmount());
                            }
                        }

                        if (hasItemsInCategory) {
                            System.out.println("---------------------------------------------------------------------------------------------------------\n\n");
                            System.out.println("---------------------------------------------------------------------------------------------------------");
                        }
                    }
                }
            }
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void listByDonor() {

        if (dmMap.isEmpty()) {
            System.out.println("No existing donors");
        } else {
            System.out.println("Donor List: \n");
            System.out.printf("%-15s %-15s %-15s %-20s %-25s %10s %n", "Donor ID", "Donation ID", "ItemID", "Item TYPE", "Description", "AMOUNT");
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
                            System.out.printf("%-15s %-15s %-15s %-20s %-25s %10.2f %n",
                                    donation.getdonorID(),
                                    donation.getDonationID(),
                                    item.getItemID(), item.getItemType(), item.getDescription(), item.getAmount()
                            );
                        }
                    }
                }
            }
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void listAll() {
        if (dmMap.isEmpty()) {
            System.out.println("No donations available.");
            return;
        }

      
        System.out.printf("%-15s %-25s %-20s %-10s %n", "DONATION ID", "DONOR NAME", "DONATION ITEMS", "AMOUNT");
        System.out.println("-------------------------------------------------------------------------");

       
        Iterator<String> mapIterator = dmMap.iterator();

       
        while (mapIterator.hasNext()) {
            String donationID = mapIterator.next();
            Donation donation = dmMap.get(donationID);

            if (donation != null) {
                String donorID = donation.getdonorID();
                String donorName = donorManagement.getDonorName(donorID);

              
                StringBuilder itemsBuilder = new StringBuilder();
                Iterator<DonationItem> itemIterator = donation.getItems().iterator();
                while (itemIterator.hasNext()) {
                    DonationItem item = itemIterator.next();
                    itemsBuilder.append(String.format("  %-20s x %.2f %n %-39s", item.getItemType(), item.getAmount(), ""));
                }

               
                System.out.printf("%-15s %-25s %s%n",
                        donation.getDonationID(),
                        donorName,
                        itemsBuilder.toString().trim()
                );
                System.out.println("-------------------------------------------------------------------------");
            }
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void filterDonations() {
      
    int choice = dmUI.selectDonationCategory();

   
    String selectedCategory = "";
    switch (choice) {
        case 1:
            selectedCategory = "Food";
            break;
        case 2:
            selectedCategory = "Cash";
            break;
        case 3:
            selectedCategory = "Daily Necessities";
            break;
        default:
            System.out.println("Invalid choice. Exiting...");
            return;
    }

    if (dmMap.isEmpty()) {
        System.out.println("No existing donors");
    } else {
        System.out.println("Enter the minimum quantity to display:");
        double minQuantity = dmUI.getInputQuantity(); 

        System.out.println("Donation List (Category: " + selectedCategory + "):\n");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        Iterator<String> iterator = dmMap.iterator();
        while (iterator.hasNext()) {
            String dnrId = iterator.next();
            Donation donation = dmMap.get(dnrId);
            if (donation != null) {
                ListInterface<DonationItem> donationItems = donation.getItems();

                if (!donationItems.isEmpty()) {
                  
                    Iterator<DonationItem> itemIterator = donationItems.iterator();
                    boolean hasItemsInCategory = false;
                    while (itemIterator.hasNext()) {
                        DonationItem item = itemIterator.next();
                        if (item.getItemType().equals(selectedCategory) && item.getAmount() > minQuantity) {
                            if (!hasItemsInCategory) {
                                System.out.printf("Donor ID: %-15s Donor Name: %-20s %n", donation.getdonorID(), donation.getDonationID());
                                System.out.println("---------------------------------------------------------------------------------------------------------");
                                hasItemsInCategory = true;
                            }
                            System.out.printf("%-15s %-20s %-25s %-10.2f %n", item.getItemID(), item.getItemType(), item.getDescription(), item.getAmount());
                        }
                    }

                    if (hasItemsInCategory) {
                        System.out.println("---------------------------------------------------------------------------------------------------------\n\n");
                        System.out.println("---------------------------------------------------------------------------------------------------------");
                    }
                }
            }
        }
    }
    ConsoleUtils.systemPause();
    ConsoleUtils.clearScreen();
    }

    private void generateDonationsSummary() {
      
        if (dmMap.isEmpty()) {
            System.out.println("No donations available to summarize.");
            return;
        }

        int totalDonations = 0;
        int totalItems = 0;
        double totalCashAmount = 0.0;
        int foodItemsCount = 0;
        int dailyNecessitiesCount = 0;
        double foodTotalAmount = 0.0;
        double dailyNecessitiesTotalAmount = 0.0;

     
        Iterator<String> mapIterator = dmMap.iterator();
        while (mapIterator.hasNext()) {
            String donationID = mapIterator.next();
            Donation donation = dmMap.get(donationID);

            if (donation != null) {
                totalDonations++;

                ListInterface<DonationItem> items = donation.getItems();
                Iterator<DonationItem> itemIterator = items.iterator();
                while (itemIterator.hasNext()) {
                    DonationItem item = itemIterator.next();
                    totalItems++;

                    if (item.getItemType().equals("Cash")) {
                        totalCashAmount += item.getAmount();
                    } else if (item.getItemType().equals("Food")) {
                        foodItemsCount++;
                        foodTotalAmount += item.getAmount();
                    } else if (item.getItemType().equals("Daily Necessities")) {
                        dailyNecessitiesCount++;
                        dailyNecessitiesTotalAmount += item.getAmount();
                    }
                }
            }
        }

       
        System.out.println("------------------------------------------------------------");
        System.out.println("Donation Summary");
        System.out.println("------------------------------------------------------------");
        System.out.println("Total Number of Donations: " + totalDonations);
        System.out.println("Total Number of Items Donated: " + totalItems);
        System.out.println("Total Cash Amount Donated: $" + String.format("%.2f", totalCashAmount));
        System.out.println("------------------------------------------------------------");
        System.out.println("Breakdown by Category:");
        System.out.println("Food: " + foodItemsCount + " items, Total Quantity: " + String.format("%.2f", foodTotalAmount));
        System.out.println("Daily Necessities: " + dailyNecessitiesCount + " items, Total Quantity: " + String.format("%.2f", dailyNecessitiesTotalAmount));
        System.out.println("------------------------------------------------------------");

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }


    public boolean donationIdExists(String donationId) {
        return dmMap.get(donationId) != null;
    }

    public Donation getDonation(String donationID) {
        
        return dmMap.get(donationID);
    }

    public List<Donation> getAllDonations() {
        List<Donation> donations = new List<>();

       
        Iterator<String> iterator = dmMap.iterator();
        while (iterator.hasNext()) {
            String donationID = iterator.next();
            Donation donation = dmMap.get(donationID);
            if (donation != null) {
                donations.add(donation);
            }
        }

        return donations;
    }

    public int getNumOfDonation() {
        int donateNum = 0;
        List<Donation> donation = new List<>();
        Iterator<String> iterator = dmMap.iterator();
        while (iterator.hasNext()) {
            donateNum++;
        }

        return donateNum;
    }

    public void removeSelectedDonation(String selectedDonationId) {
        if (dmMap.get(selectedDonationId) != null) {
            dmMap.remove(selectedDonationId);
        }
    }

    public static void main(String[] args) {
        DonorManagement donorManagement = new DonorManagement();
        DonationManagement donationManagement = new DonationManagement(donorManagement); 
        donationManagement.dmstart();
    }
}