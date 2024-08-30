/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;
import utility.*;
import dao.*;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author evansleong
 */
public class DoneeManagement {

    DoneeManagementUI ui = new DoneeManagementUI();
    LinkedHashMapInterface<String, Donee> doneeMap;
    private int doneeCounter = 11;
    private final DonationManagement donationManagement;
    private final DoneeDataSeeder dataSeeder;

    public DoneeManagement(DonationManagement donationManagement) {
        doneeMap = new LinkedHashMap<>();
        this.donationManagement = donationManagement;
        dataSeeder = new DoneeDataSeeder();
        initializeDoneeMap();
    }

    public void start() {
        ConsoleUtils.clearScreen();
        boolean running = true;
        while (running) {
            int choice = ui.getMenuChoice();
            switch (choice) {
                case 1:
                    addDonee();
                    break;
                case 2:
                    manageDonees();
                    break;
                case 3:
                    updateDonee();
                    break;
                case 4:
                    searchDonee();
                    break;
                case 5:
                    listDonees();
                    break;
                case 6:
                    filterDonees();
                    break;
                case 7:
                    sortDoneesByName();
                    break;
                case 8:
                    generateReports();
                    break;
                case 9:
                    addDonationToDonee();
                    break;
                case 0:
                    running = false;
                    ConsoleUtils.clearScreen();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addDonee() {
        String type = ui.inputDoneeType();
        String fullId = generateDoneeId();

        System.out.println("Donee ID: " + fullId);

        String name = ui.inputDoneeName();
        String contactInfo = ui.inputContactInfo();

        Integer age = null;
        if ("Individual".equalsIgnoreCase(type)) {
            age = ui.inputAge();
        }

        Donee donee = new Donee(fullId, name, contactInfo, type, age);
        if (confirmAction()) {
            doneeMap.put(fullId, donee);
            System.out.println("Donee added successfully.");
        } else {
            System.out.println("Action cancelled.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void manageDonees() {
        int choice = ui.promptRemoveOrClearDonees();

        if (choice == 1) {
            removeDonee();
        } else if (choice == 2) {
            clearDonees();
        }
    }

    private void removeDonee() {
        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;

        if (doneeMap.containsKey(fullId)) {
            Donee donee = doneeMap.get(fullId);

            ui.displayDoneeDetails(donee);

            if (confirmAction()) {
                doneeMap.remove(fullId);
                System.out.println("Donee removed successfully.");
            } else {
                System.out.println("Action cancelled.");
            }
        } else {
            System.out.println("Donee not found.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void clearDonees() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        displayDoneeList();

        if (confirmAction()) {
            doneeMap.clear();
            System.out.println("All donees have been cleared.");
        } else {
            System.out.println("Action cancelled.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void updateDonee() {
        displayDoneeList();

        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;

        if (doneeMap.containsKey(fullId)) {
            Donee originalDonee = doneeMap.get(fullId);
            Donee updatedDonee;

            int choice = ui.promptModifyOneorModifyAll();

            switch (choice) {
                case 1:
                    boolean updating = true;
                    while (updating) {
                        int updateChoice = ui.getUpdateFieldChoice();
                        switch (updateChoice) {
                            case 1:
                                String name = ui.inputDoneeName();
                                updatedDonee = new Donee(
                                        originalDonee.getDoneeId(),
                                        name,
                                        originalDonee.getDoneeContact(),
                                        originalDonee.getDoneeType(),
                                        originalDonee.getAge()
                                );
                                doneeMap.replace(fullId, updatedDonee);
                                System.out.println("Name updated successfully.");
                                break;
                            case 2:
                                String contactInfo = ui.inputContactInfo();
                                updatedDonee = new Donee(
                                        originalDonee.getDoneeId(),
                                        originalDonee.getDoneeName(),
                                        contactInfo,
                                        originalDonee.getDoneeType(),
                                        originalDonee.getAge()
                                );
                                doneeMap.replace(fullId, updatedDonee);
                                System.out.println("Contact info updated successfully.");
                                break;
                            case 3:
                                String type = ui.inputDoneeType();
                                Integer age = null;
                                if (type.equalsIgnoreCase("individual")) {
                                    age = ui.inputAge();
                                }
                                updatedDonee = new Donee(
                                        originalDonee.getDoneeId(),
                                        originalDonee.getDoneeName(),
                                        originalDonee.getDoneeContact(),
                                        type,
                                        age
                                );
                                doneeMap.replace(fullId, updatedDonee);
                                System.out.println("Type and age updated successfully.");
                                break;
                            case 0:
                                updating = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 2:
                    String name = ui.inputDoneeName();
                    String contactInfo = ui.inputContactInfo();
                    String type = ui.inputDoneeType();
                    Integer age = null;
                    if (type.equalsIgnoreCase("individual")) {
                        age = ui.inputAge();
                    }
                    updatedDonee = new Donee(fullId, name, contactInfo, type, age);
                    updatedDonee.setDonations(originalDonee.getDonations());
                    doneeMap.replace(fullId, updatedDonee);
                    System.out.println("Donee updated successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Donee not found.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void searchDonee() {
        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;

        if (doneeMap.containsKey(fullId)) {
            Donee donee = doneeMap.get(fullId);
            // Use the UI method to display donee details
            ui.displayDoneeDetails(donee);
        } else {
            System.out.println("Donee not found.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    public void listDonees() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        ui.displayDoneeListHeader();

        Iterator<String> doneeIterator = doneeMap.iterator();
        while (doneeIterator.hasNext()) {
            String doneeId = doneeIterator.next();
            Donee donee = doneeMap.get(doneeId);

            if (donee != null) {
                int foodCount = 0;
                int dailyNecessitiesCount = 0;
                double cashCount = 0;

                LinkedHashMapInterface<String, Donation> donations = donee.getDonations();
                if (donations != null && !donations.isEmpty()) {
                    Iterator<String> donationIterator = donations.iterator();
                    while (donationIterator.hasNext()) {
                        String donationId = donationIterator.next();
                        Donation donation = donations.get(donationId);
                        if (donation != null) {
                            Iterator<DonationItem> itemIterator = donation.getItems().iterator();
                            while (itemIterator.hasNext()) {
                                DonationItem item = itemIterator.next();
                                switch (item.getItemType()) {
                                    case "Food":
                                        foodCount += item.getAmount();
                                        break;
                                    case "Daily Necessities":
                                        dailyNecessitiesCount += item.getAmount();
                                        break;
                                    case "Cash":
                                        cashCount += item.getAmount();
                                        break;
                                }
                            }
                        }
                    }
                }

                ui.displayDoneeWithDonations(donee, foodCount, dailyNecessitiesCount, cashCount);
            }
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void sortDoneesByName() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        int choice = ui.promptAscendingorDescending();

        Comparator<Donee> comparator;
        switch (choice) {
            case 1:
                comparator = doneeNameComparator; // Ascending order
                break;
            case 2:
                comparator = doneeNameComparator.reversed(); // Descending order
                break;
            default:
                System.out.println("Invalid choice. Defaulting to ascending order.");
                comparator = doneeNameComparator;
                break;
        }

        Donee[] doneeArray = new Donee[doneeMap.size()];
        Iterator<String> iterator = doneeMap.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            String fullId = iterator.next();
            Donee donee = doneeMap.get(fullId);
            if (donee != null) {
                doneeArray[index++] = donee;
            }
        }

        doneeMap.mergeSort(doneeArray, comparator);

        ui.displaySortedDoneeList(doneeArray);

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void filterDonees() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        int filterType = ui.inputFilterCriteria();
        switch (filterType) {
            case 1:
                String type = ui.inputDoneeType();
                ui.displayFilteredDoneeHeader("Donees of type: " + type, false, false);

                Iterator<String> iterator = doneeMap.iterator();
                while (iterator.hasNext()) {
                    String fullId = iterator.next();
                    Donee donee = doneeMap.get(fullId);
                    if (donee != null && donee.getDoneeType().equalsIgnoreCase(type)) {
                        System.out.printf("%-15s %-20s %-25s %-15s\n",
                                donee.getDoneeId(),
                                donee.getDoneeName(),
                                donee.getDoneeContact(),
                                donee.getDoneeType(),
                                donee.getAge());
                    }
                }
                break;

            case 2:
                String firstLetter = ui.inputFirstLetterOfDoneeName();
                ui.displayFilteredDoneeHeader("Donees with names starting with: " + firstLetter.toUpperCase(), false, false);

                Iterator<String> nameIterator = doneeMap.iterator();
                while (nameIterator.hasNext()) {
                    String fullId = nameIterator.next();
                    Donee donee = doneeMap.get(fullId);
                    if (donee != null && donee.getDoneeName().startsWith(firstLetter.toUpperCase())) {
                        System.out.printf("%-15s %-20s %-25s %-15s\n",
                                donee.getDoneeId(),
                                donee.getDoneeName(),
                                donee.getDoneeContact(),
                                donee.getDoneeType(),
                                donee.getAge());
                    }
                }
                break;

            case 3:
                int ageGroup = ui.inputAgeGroup();
                String ageGroupLabel = ui.getAgeGroupLabel(ageGroup);
                if (ageGroupLabel == null) {
                    System.out.println("Invalid age group selection.");
                    return;
                }

                ui.displayFilteredDoneeHeader("Donees in the age group: " + ageGroupLabel, true, true);

                Iterator<String> ageIterator = doneeMap.iterator();
                while (ageIterator.hasNext()) {
                    String fullId = ageIterator.next();
                    Donee donee = doneeMap.get(fullId);
                    if (donee != null && "Individual".equalsIgnoreCase(donee.getDoneeType())) {
                        if (ui.doesAgeMatchGroup(donee.getAge(), ageGroup)) {
                            ui.displayFilteredDonee(donee, true, true, ageGroupLabel);
                        }
                    }
                }
                break;

            default:
                System.out.println("Invalid filter type. Please try again.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void generateReports() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        int individualCount = 0;
        int organizationCount = 0;
        int familyCount = 0;

        double[] individualDonations = new double[3];
        double[] organizationDonations = new double[3];
        double[] familyDonations = new double[3];

        Iterator<String> doneeIterator = doneeMap.iterator();
        while (doneeIterator.hasNext()) {
            String doneeId = doneeIterator.next();
            Donee donee = doneeMap.get(doneeId);

            if (donee != null) {
                String type = donee.getDoneeType().toLowerCase();
                double[] donationCounts = null;

                switch (type) {
                    case "individual":
                        individualCount++;
                        donationCounts = individualDonations;
                        break;
                    case "organization":
                        organizationCount++;
                        donationCounts = organizationDonations;
                        break;
                    case "family":
                        familyCount++;
                        donationCounts = familyDonations;
                        break;
                    default:
                        continue;
                }

                LinkedHashMapInterface<String, Donation> donations = donee.getDonations();
                if (donations != null && !donations.isEmpty()) {
                    Iterator<String> donationIterator = donations.iterator();
                    while (donationIterator.hasNext()) {
                        String donationId = donationIterator.next();
                        Donation donation = donations.get(donationId);
                        if (donation != null) {
                            Iterator<DonationItem> itemIterator = donation.getItems().iterator();
                            while (itemIterator.hasNext()) {
                                DonationItem item = itemIterator.next();
                                switch (item.getItemType()) {
                                    case "Food":
                                        donationCounts[0] += item.getAmount();
                                        break;
                                    case "Daily Necessities":
                                        donationCounts[1] += item.getAmount();
                                        break;
                                    case "Cash":
                                        donationCounts[2] += item.getAmount();
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }

        int totalDonees = individualCount + organizationCount + familyCount;

        ui.displaySummaryReport(
                individualCount, organizationCount, familyCount,
                individualDonations, organizationDonations, familyDonations,
                totalDonees
        );

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private boolean checkDoneeIsEmpty() {
        if (doneeMap.isEmpty()) {
            System.out.println("No donees available.");
            ConsoleUtils.systemPause();
            ConsoleUtils.clearScreen();
            return true;
        }
        return false;
    }

    private void displayDoneeList() {
        if (doneeMap.isEmpty()) {
            System.out.println("No donees available.");
            return;
        }

        System.out.printf("%-15s %-20s %-15s\n", "DONEE ID", "DONEE NAME", "CONTACT NO");
        System.out.println("----------------------------------------------");

        Iterator<String> iterator = doneeMap.iterator();
        while (iterator.hasNext()) {
            String fullId = iterator.next();
            Donee donee = doneeMap.get(fullId);

            if (donee != null) {
                System.out.printf("%-15s %-20s %-15s\n",
                        donee.getDoneeId(),
                        donee.getDoneeName(),
                        donee.getDoneeContact());
            }
        }
    }

    private void addDonationToDonee() {
        String doneeId = ui.inputDoneeID();
        String fullDoneeId = "DNE-" + doneeId;

        if (doneeMap.containsKey(fullDoneeId)) {
            Donee donee = doneeMap.get(fullDoneeId);

            System.out.println("Donee Name: " + donee.getDoneeName());

            List<Donation> availableDonations = donationManagement.getAllDonations();
            if (availableDonations.isEmpty()) {
                System.out.println("No available donations at the moment.");
                return;
            }

            // Print table header with adjusted column widths
            System.out.printf("\n%-15s %-35s %-35s %-25s\n", "Donation ID", "Food Items(pcs/set)", "Daily Necessities(pcs/set)", "Cash Amount(RM)");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

            // Iterate over available donations
            Iterator<Donation> donationIterator = availableDonations.iterator();
            while (donationIterator.hasNext()) {
                Donation donation = donationIterator.next();
                String donationID = donation.getDonationID();

                StringBuilder foodItems = new StringBuilder();
                StringBuilder dailyNecessitiesItems = new StringBuilder();
                double totalCashAmount = 0.0;

                // Iterate over donation items
                Iterator<DonationItem> itemIterator = donation.getItems().iterator();
                while (itemIterator.hasNext()) {
                    DonationItem item = itemIterator.next();
                    String description = item.getDescription();
                    String itemType = item.getItemType();
                    double amount = item.getAmount();

                    String itemDescription = description + " x " + (int) amount;

                    if ("Food".equals(itemType)) {
                        if (foodItems.length() > 0) {
                            foodItems.append(", ");
                        }
                        foodItems.append(itemDescription);
                    } else if ("Daily Necessities".equals(itemType)) {
                        if (dailyNecessitiesItems.length() > 0) {
                            dailyNecessitiesItems.append(", ");
                        }
                        dailyNecessitiesItems.append(itemDescription);
                    } else if ("Cash".equals(itemType)) {
                        totalCashAmount += amount;
                    }
                }

                // Format strings for output
                String foodItemsStr = foodItems.length() > 0 ? foodItems.toString() : "None";
                String dailyNecessitiesItemsStr = dailyNecessitiesItems.length() > 0 ? dailyNecessitiesItems.toString() : "None";
                String cashItemsStr = totalCashAmount > 0 ? String.format("%.2f", totalCashAmount) : "None";

                // Print donation details with adjusted column widths
                System.out.printf("%-15s %-35s %-35s %-25s\n", donationID, foodItemsStr, dailyNecessitiesItemsStr, cashItemsStr);
            }

            // Input donation ID to add to donee
            String donationID = ui.inputDonationID();
            String fullDonationId = "DON-" + donationID;

            if (donationManagement.donationIdExists(fullDonationId)) {
                Donation donation = donationManagement.getDonation(fullDonationId);

                donee.addDonation(fullDonationId, donation);

                System.out.println("Donation successfully assigned to Donee.");
            } else {
                System.out.println("Donation ID not found. Please check the ID and try again.");
            }
            ConsoleUtils.systemPause();
            ConsoleUtils.clearScreen();
        } else {
            System.out.println("Donee ID not found in the system. Please check the ID and try again.");
        }
    }

    private String generateDoneeId() {
        return String.format("DNE-%03d", doneeCounter++); // Format ID as "DNE-001", "DNE-002", etc.
    }

    private boolean confirmAction() {
        char confirmation = ui.confirmAction();
        return confirmation == 'Y';
    }

    private Comparator<Donee> doneeNameComparator = new Comparator<Donee>() {
        @Override
        public int compare(Donee d1, Donee d2) {
            return d1.getDoneeName().compareTo(d2.getDoneeName());
        }
    };

    private void initializeDoneeMap() {
        this.doneeMap = dataSeeder.getDoneeMap();
    }

    public static void main(String[] args) {
        DonorManagement donorManagement = new DonorManagement();
        DonationManagement donationManagement = new DonationManagement(donorManagement);
        DoneeManagement doneeManagement = new DoneeManagement(donationManagement);
        doneeManagement.start();
    }
}
