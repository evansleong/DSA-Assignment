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
 * @author Leong Gao Chong
 */
public class DoneeManagement {

    DoneeManagementUI ui = new DoneeManagementUI();
    LinkedHashMapInterface<String, Donee> doneeMap = new LinkedHashMap<>();
    private int doneeCounter = 11;
    private final DonationManagement donationManagement;
    private final DoneeDataSeeder dataSeeder;

    public DoneeManagement(DonationManagement donationManagement) {
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
                    consoleUtilise();
                    break;
                case 2:
                    manageDonees();
                    consoleUtilise();
                    break;
                case 3:
                    updateDonee();
                    consoleUtilise();
                    break;
                case 4:
                    searchDonee();
                    break;
                case 5:
                    listDonees();
                    consoleUtilise();
                    break;
                case 6:
                    filterDonees();
                    consoleUtilise();
                    break;
                case 7:
                    sortDoneesByName();
                    consoleUtilise();
                    break;
                case 8:
                    generateReports();
                    consoleUtilise();
                    break;
                case 9:
                    addDonationToDonee();
                    consoleUtilise();
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
        if (checkDoneeIsEmpty()) {
            return;
        }

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
        consoleUtilise();
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
    }

    private void updateDonee() {
        if (checkDoneeIsEmpty()) {
            return;
        }

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

    }

    private void searchDonee() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;

        if (doneeMap.containsKey(fullId)) {
            Donee donee = doneeMap.get(fullId);
            ui.displayDoneeDetails(donee);
        } else {
            System.out.println("Donee not found.");
        }
        consoleUtilise();
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
    }

    private void sortDoneesByName() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        int choice = ui.promptAscendingorDescending();

        Comparator<Donee> comparator;
        switch (choice) {
            case 1:
                comparator = doneeNameComparator;
                break;
            case 2:
                comparator = doneeNameComparator.reversed();
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

    }

    private void generateReports() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        int reportType = ui.chooseReportType();

        switch (reportType) {
            case 1:
                generateTotalDonationReport();
                break;
            case 2:
                generateTopReports();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
                break;
        }
    }

    private void generateTotalDonationReport() {
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
    }

    private void generateTopReports() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        double[] individualDonations = new double[3];
        double[] organizationDonations = new double[3];
        double[] familyDonations = new double[3];

        String[] topFoodDoneeIds = new String[3];
        double[] topFoodAmounts = new double[3];

        String[] topDailyNecessitiesDoneeIds = new String[3];
        double[] topDailyNecessitiesAmounts = new double[3];

        String[] topCashDoneeIds = new String[3];
        double[] topCashAmounts = new double[3];

        Iterator<String> doneeIterator = doneeMap.iterator();
        while (doneeIterator.hasNext()) {
            String doneeId = doneeIterator.next();
            Donee donee = doneeMap.get(doneeId);

            if (donee != null) {
                String type = donee.getDoneeType().toLowerCase();
                double[] donationCounts = null;

                switch (type) {
                    case "individual":
                        donationCounts = individualDonations;
                        break;
                    case "organization":
                        donationCounts = organizationDonations;
                        break;
                    case "family":
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
                                double amount = item.getAmount();
                                switch (item.getItemType()) {
                                    case "Food":
                                        donationCounts[0] += amount;
                                        updateTop3Donees(topFoodDoneeIds, topFoodAmounts, doneeId, amount);
                                        break;
                                    case "Daily Necessities":
                                        donationCounts[1] += amount;
                                        updateTop3Donees(topDailyNecessitiesDoneeIds, topDailyNecessitiesAmounts, doneeId, amount);
                                        break;
                                    case "Cash":
                                        donationCounts[2] += amount;
                                        updateTop3Donees(topCashDoneeIds, topCashAmounts, doneeId, amount);
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }

        ui.displayTop3DoneesByCategoryReport(
                topFoodDoneeIds, topFoodAmounts,
                topDailyNecessitiesDoneeIds, topDailyNecessitiesAmounts,
                topCashDoneeIds, topCashAmounts
        );
    }

    private void addDonationToDonee() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        listDonees();

        String doneeId = ui.inputDoneeID();
        String fullDoneeId = "DNE-" + doneeId;

        if (doneeMap.containsKey(fullDoneeId)) {
            Donee donee = doneeMap.get(fullDoneeId);

            System.out.println("Donee Name: " + donee.getDoneeName());

            ListInterface<Donation> availableDonations = donationManagement.getAllDonations();
            if (availableDonations.isEmpty()) {
                System.out.println("No available donations at the moment.");
                return;
            }

            System.out.printf("\n%-15s %-35s %-35s %-25s\n", "Donation ID", "Food Items(pcs/set)", "Daily Necessities(pcs/set)", "Cash Amount(RM)");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

            Iterator<Donation> donationIterator = availableDonations.iterator();
            while (donationIterator.hasNext()) {
                Donation donation = donationIterator.next();
                String donationID = donation.getDonationID();

                StringBuilder foodItems = new StringBuilder();
                StringBuilder dailyNecessitiesItems = new StringBuilder();
                double totalCashAmount = 0.0;

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

                String foodItemsStr = foodItems.length() > 0 ? foodItems.toString() : "N/A";
                String dailyNecessitiesItemsStr = dailyNecessitiesItems.length() > 0 ? dailyNecessitiesItems.toString() : "N/A";
                String cashItemsStr = totalCashAmount > 0 ? String.format("RM %.2f", totalCashAmount) : "N/A";

                System.out.printf("%-15s %-35s %-35s %-25s\n", donationID, foodItemsStr, dailyNecessitiesItemsStr, cashItemsStr);
            }

            String donationID = ui.inputDonationID();
            String fullDonationId = "DON-" + donationID;

            if (donationManagement.donationIdExists(fullDonationId)) {
                Donation donation = donationManagement.getDonation(fullDonationId);

                donee.addDonation(fullDonationId, donation);
                donationManagement.removeSelectedDonation(fullDonationId);

                System.out.println("Donation successfully assigned to Donee.");
            } else {
                System.out.println("Donation ID not found. Please check the ID and try again.");
            }
        } else {
            System.out.println("Donee ID not found in the system. Please check the ID and try again.");
        }
    }

    private String generateDoneeId() {
        return String.format("DNE-%03d", doneeCounter++); // Format ID as "DNE-001", "DNE-002", etc.
    }

    private boolean checkDoneeIsEmpty() {
        if (doneeMap.isEmpty()) {
            System.out.println("No donees available.");
            consoleUtilise();
            return true;
        }
        return false;
    }

    private void displayDoneeList() {
        if (checkDoneeIsEmpty()) {
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

    private void updateTop3Donees(String[] topDoneeIds, double[] topAmounts, String doneeId, double amount) {
        for (int i = 0; i < 3; i++) {
            if (amount > topAmounts[i]) {
                for (int j = 2; j > i; j--) {
                    topAmounts[j] = topAmounts[j - 1];
                    topDoneeIds[j] = topDoneeIds[j - 1];
                }
                topAmounts[i] = amount;
                topDoneeIds[i] = doneeId;
                break;
            }
        }
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

    private void consoleUtilise() {
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    public static void main(String[] args) {
        DonorManagement donorManagement = new DonorManagement();
        DonationManagement donationManagement = new DonationManagement(donorManagement);
        DoneeManagement doneeManagement = new DoneeManagement(donationManagement);
        doneeManagement.start();
    }
}
