/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;
import utility.*;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author evansleong
 */
public class DoneeManagement {

    DoneeManagementUI ui = new DoneeManagementUI();
    LinkedHashMapInterface<String, Donee> doneeMap;
    private int doneeCounter = 1; // Counter for generating donee IDs
//    DonationManagement donationManagement; // Reference to DonationManagement

    public DoneeManagement() {
        doneeMap = new LinkedHashMap<>();
//        this.donationManagement = donationManagement; // Initialize the donation management reference
        seedData();
    }

    private void seedData() {
        // Create donees
        Donee johnDoe = new Donee("DNE-001", "John Doe", "555-1234", "Individual", 30); // Example age
        Donee janeSmith = new Donee("DNE-002", "Jane Smith", "555-5678", "Individual", 25); // Example age
        Donee charityOrgA = new Donee("DNE-003", "Charity Org A", "555-9876", "Organization", null);
        Donee familyB = new Donee("DNE-004", "Family B", "555-4321", "Family", null);
        Donee johnsBakery = new Donee("DNE-005", "John's Bakery", "555-7654", "Organization", null);
        Donee doeFamily = new Donee("DNE-006", "Doe Family", "555-2468", "Family", null);
        Donee helpingHands = new Donee("DNE-007", "Helping Hands", "555-1357", "Organization", null);
        Donee mariaGreen = new Donee("DNE-008", "Maria Green", "555-2468", "Individual", 40); // Example age
        Donee smithFamily = new Donee("DNE-009", "Smith Family", "555-3579", "Family", null);
        Donee communityAid = new Donee("DNE-010", "Community Aid", "555-0987", "Organization", null);

        // Add donees to the map
        doneeMap.put(johnDoe.getDoneeId(), johnDoe);
        doneeMap.put(janeSmith.getDoneeId(), janeSmith);
        doneeMap.put(charityOrgA.getDoneeId(), charityOrgA);
        doneeMap.put(familyB.getDoneeId(), familyB);
        doneeMap.put(johnsBakery.getDoneeId(), johnsBakery);
        doneeMap.put(doeFamily.getDoneeId(), doeFamily);
        doneeMap.put(helpingHands.getDoneeId(), helpingHands);
        doneeMap.put(mariaGreen.getDoneeId(), mariaGreen);
        doneeMap.put(smithFamily.getDoneeId(), smithFamily);
        doneeMap.put(communityAid.getDoneeId(), communityAid);

        // Create donations
        Donation donation1 = new Donation("DON-001", "Food", "Rice and beans", "2024-08-01", "DON-002");
        Donation donation2 = new Donation("DON-002", "Clothing", "Winter coats", "2024-08-05", "DON-003");
        Donation donation3 = new Donation("DON-003", "Monetary", "Cash donation", "2024-08-10", "DON-004");
        Donation donation4 = new Donation("DON-004", "Food", "Canned goods", "2024-08-15", "DON-001");

        // Add donations to donees
        johnDoe.addDonation(donation1.getDonationId(), donation1);
        janeSmith.addDonation(donation2.getDonationId(), donation2);
        janeSmith.addDonation(donation2.getDonationId(), donation4);
        charityOrgA.addDonation(donation3.getDonationId(), donation3);
        familyB.addDonation(donation4.getDonationId(), donation4);
        johnsBakery.addDonation(donation1.getDonationId(), donation1);
        doeFamily.addDonation(donation2.getDonationId(), donation2);
        helpingHands.addDonation(donation3.getDonationId(), donation3);
        mariaGreen.addDonation(donation4.getDonationId(), donation4);
        smithFamily.addDonation(donation1.getDonationId(), donation1);
        communityAid.addDonation(donation3.getDonationId(), donation3);
    }

    public void start() {
        boolean running = true;
        while (running) {
            int choice = ui.getMenuChoice();
            switch (choice) {
                case 1:
                    addDonee();
                    break;
                case 2:
                    removeDonee();
                    break;
                case 3:
                    clearDonees();
                    break;
                case 4:
                    updateDonee();
                    break;
                case 5:
                    searchDonee();
                    break;
                case 6:
                    listDonees();
                    break;
                case 7:
                    filterDonees();
                    break;
                case 8:
                    sortDoneesByName();
                    break;
                case 9:
                    generateReports();
                    break;
//                case 10:
//                    addDonationToDonee();
//                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private String generateDoneeId() {
        return String.format("DNE-%03d", doneeCounter++); // Format ID as "DNE-001", "DNE-002", etc.
    }

    private void addDonee() {
        String type = ui.inputDoneeType();
        String fullId = generateDoneeId(); // Generate a new unique ID

        // Display the generated ID
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

    private void removeDonee() {
        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;
        Donee donee = doneeMap.get(fullId);

        if (donee != null) {
            // Display donee details to confirm
            System.out.println("Donee Details:");
            System.out.printf("%-15s: %s\n", "ID", donee.getDoneeId());
            System.out.printf("%-15s: %s\n", "Name", donee.getDoneeName());
            System.out.printf("%-15s: %s\n", "Contact", donee.getDoneeContact());
            System.out.printf("%-15s: %s\n", "Type", donee.getDoneeType());
            System.out.println();

            // Prompt for confirmation
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
        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;
        Donee donee = doneeMap.get(fullId);

        if (donee != null) {
            boolean updating = true;
            while (updating) {
                int updateChoice = ui.getUpdateFieldChoice();
                switch (updateChoice) {
                    case 1:
                        String name = ui.inputDoneeName();
                        donee.setDoneeName(name);
                        System.out.println("Name updated successfully.");
                        break;
                    case 2:
                        String contactInfo = ui.inputContactInfo();
                        donee.setDoneeContact(contactInfo);
                        System.out.println("Contact info updated successfully.");
                        break;
                    case 3:
                        String type = ui.inputDoneeType();
                        donee.setDoneeType(type);
                        System.out.println("Type updated successfully.");
                        break;
                    case 0:
                        updating = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
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
        Donee donee = doneeMap.get(fullId);

        if (donee != null) {
            System.out.println("Donee Details:");
            // Print the details in a formatted way
            System.out.printf("%-15s: %s\n", "ID", donee.getDoneeId());
            System.out.printf("%-15s: %s\n", "Name", donee.getDoneeName());
            System.out.printf("%-15s: %s\n", "Contact", donee.getDoneeContact());
            System.out.printf("%-15s: %s\n", "Type", donee.getDoneeType());
            System.out.println();
        } else {
            System.out.println("Donee not found.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void listDonees() {
        if (doneeMap.isEmpty()) {
            System.out.println("No donees available.");
            return;
        }

        // Create an array to hold donees
        Donee[] doneeArray = new Donee[doneeMap.size()];
        Iterator<String> iterator = doneeMap.iterator();
        int index = 0;

        // Populate the doneeArray with donees from doneeMap
        while (iterator.hasNext()) {
            String fullId = iterator.next();
            Donee donee = doneeMap.get(fullId);
            if (donee != null) {
                doneeArray[index++] = donee;
            }
        }

        // Display the donees with donation type summary
        System.out.println("Donees List:");
        System.out.printf("%-15s %-20s %-25s %-15s %-20s\n", "ID", "Name", "Contact Info", "Type", "Donation Type");
        System.out.println("------------------------------------------------------------------------------------------------------");

        for (Donee donee : doneeArray) {
            if (donee != null) {
                // Initialize a map to count donation types
                LinkedHashMapInterface<String, Integer> donationTypeCount = new LinkedHashMap<>();
                LinkedHashMapInterface<String, Donation> donations = donee.getDonations();

                // Count donations by type
                if (donations != null) {
                    Iterator<String> donationIterator = donations.iterator();
                    while (donationIterator.hasNext()) {
                        String donationId = donationIterator.next();
                        Donation donation = donations.get(donationId);
                        if (donation != null) {
                            String type = donation.getDonationType();
                            if (donationTypeCount.containsKey(type)) {
                                // Increment the count
                                int currentCount = donationTypeCount.get(type);
                                donationTypeCount.put(type, currentCount + 1);
                            } else {
                                // Initialize the count
                                donationTypeCount.put(type, 1);
                            }
                        }
                    }
                }

                // Print donee details
                System.out.printf("%-15s %-20s %-25s %-15s\n", donee.getDoneeId(), donee.getDoneeName(), donee.getDoneeContact(), donee.getDoneeType());

                // Print donation types
                Iterator<String> typeIterator = donationTypeCount.iterator();
                if (!typeIterator.hasNext()) {
                    System.out.println("None");
                } else {
                    while (typeIterator.hasNext()) {
                        String type = typeIterator.next();
                        int count = donationTypeCount.get(type);
                        System.out.printf("%-15s %-20s x%d\n", "", type, count);
                    }
                }
            }
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void sortDoneesByName() {
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
        doneeMap.mergeSort(doneeArray, doneeNameComparator);
        ui.displaySortedDoneeList(doneeArray);

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void filterDonees() {
        int filterType = ui.inputFilterCriteria();
        switch (filterType) {
            case 1:
                // Filter by donee type
                String type = ui.inputDoneeType();
                System.out.println("Donees of type: " + type);
                // Print table header
                System.out.printf("%-15s %-20s %-25s %-15s\n", "ID", "Name", "Contact Info", "Type");
                System.out.println("-----------------------------------------------------------------------------------");

                // Print each donee's details in a formatted way
                Iterator<String> iterator = doneeMap.iterator();
                while (iterator.hasNext()) {
                    String fullId = iterator.next();
                    Donee donee = doneeMap.get(fullId);
                    if (donee != null && donee.getDoneeType().equalsIgnoreCase(type)) {
                        System.out.printf("%-15s %-20s %-25s %-15s\n",
                                donee.getDoneeId(),
                                donee.getDoneeName(),
                                donee.getDoneeContact(),
                                donee.getDoneeType());
                    }
                }
                break;

            case 2:
                // Filter by donee name's starting letter
                String firstLetter = ui.inputFirstLetterOfDoneeName();
                System.out.println("Donees with names starting with: " + firstLetter.toUpperCase());
                // Print table header
                System.out.printf("%-15s %-20s %-25s %-15s\n", "ID", "Name", "Contact Info", "Type");
                System.out.println("-----------------------------------------------------------------------------------");

                // Print each donee's details in a formatted way
                Iterator<String> nameIterator = doneeMap.iterator();
                while (nameIterator.hasNext()) {
                    String fullId = nameIterator.next();
                    Donee donee = doneeMap.get(fullId);
                    if (donee != null && donee.getDoneeName().startsWith(firstLetter.toUpperCase())) {
                        System.out.printf("%-15s %-20s %-25s %-15s\n",
                                donee.getDoneeId(),
                                donee.getDoneeName(),
                                donee.getDoneeContact(),
                                donee.getDoneeType());
                    }
                }
                break;

            case 3:
                int ageGroup = ui.inputAgeGroup();

                String ageGroupLabel = "";
                switch (ageGroup) {
                    case 1:
                        ageGroupLabel = "Kid";
                        break;
                    case 2:
                        ageGroupLabel = "Teenager";
                        break;
                    case 3:
                        ageGroupLabel = "Adult";
                        break;
                    case 4:
                        ageGroupLabel = "Senior Citizen";
                        break;
                    default:
                        System.out.println("Invalid age group selection.");
                        return;
                }

                System.out.println("Donees in the age group: " + ageGroupLabel);
                // Print table header
                System.out.printf("%-15s %-20s %-25s %-15s %-15s\n", "ID", "Name", "Contact Info", "Type", "Age Group");
                System.out.println("-----------------------------------------------------------------------------------");

                // Print each donee's details in a formatted way
                Iterator<String> ageIterator = doneeMap.iterator();
                while (ageIterator.hasNext()) {
                    String fullId = ageIterator.next();
                    Donee donee = doneeMap.get(fullId);
                    if (donee != null && "Individual".equalsIgnoreCase(donee.getDoneeType())) {
                        Integer age = donee.getAge();
                        if (age != null) {
                            boolean matches = false;
                            switch (ageGroup) {
                                case 1:
                                    matches = (age >= 1 && age <= 20);
                                    break;
                                case 2:
                                    matches = (age >= 21 && age <= 34);
                                    break;
                                case 3:
                                    matches = (age >= 35 && age <= 54);
                                    break;
                                case 4:
                                    matches = (age >= 55);
                                    break;
                            }
                            if (matches) {
                                System.out.printf("%-15s %-20s %-25s %-15s %-15s\n",
                                        donee.getDoneeId(),
                                        donee.getDoneeName(),
                                        donee.getDoneeContact(),
                                        donee.getDoneeType(),
                                        ageGroupLabel);
                            }
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
        int totalDonees = doneeMap.size();
        int individualCount = 0;
        int organizationCount = 0;
        int familyCount = 0;

        // Donation type statistics
        LinkedHashMapInterface<String, Integer> donationTypeCount = new LinkedHashMap<>();
        int totalDonations = 0;

        Iterator<String> iterator = doneeMap.iterator();
        while (iterator.hasNext()) {
            String fullId = iterator.next();
            Donee donee = doneeMap.get(fullId);
            if (donee != null) {
                // Count donee types
                switch (donee.getDoneeType().toLowerCase()) {
                    case "individual":
                        individualCount++;
                        break;
                    case "organization":
                        organizationCount++;
                        break;
                    case "family":
                        familyCount++;
                        break;
                }

                // Count donation types
                LinkedHashMapInterface<String, Donation> donations = donee.getDonations();
                if (donations != null) {
                    Iterator<String> donationIterator = donations.iterator();
                    while (donationIterator.hasNext()) {
                        String donationId = donationIterator.next();
                        Donation donation = donations.get(donationId);
                        if (donation != null) {
                            String type = donation.getDonationType();
                            if (donationTypeCount.containsKey(type)) {
                                // Increment the count
                                int currentCount = donationTypeCount.get(type);
                                donationTypeCount.put(type, currentCount + 1);
                            } else {
                                // Initialize the count
                                donationTypeCount.put(type, 1);
                            }
                            totalDonations++;
                        }
                    }
                }
            }
        }

        // Display report
        System.out.println("********** Summary Report **********");
        System.out.println();

        // Donee type distribution
        System.out.printf("Total Donees: %d\n", totalDonees);
        System.out.printf("Individual Donees: %d (%.2f%%)\n", individualCount, (individualCount / (double) totalDonees) * 100);
        System.out.printf("Organization Donees: %d (%.2f%%)\n", organizationCount, (organizationCount / (double) totalDonees) * 100);
        System.out.printf("Family Donees: %d (%.2f%%)\n", familyCount, (familyCount / (double) totalDonees) * 100);
        System.out.println();

        // Donation type statistics
        System.out.println("Donation Type Distribution:");
        System.out.printf("%-15s %-15s\n", "Donation Type", "Count");
        System.out.println("------------------------------");
        Iterator<String> typeIterator = donationTypeCount.iterator();
        while (typeIterator.hasNext()) {
            String type = typeIterator.next();
            int count = donationTypeCount.get(type);
            System.out.printf("%-15s %-15d\n", type, count);
        }
        System.out.println();

        System.out.println("************************************");
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

//    private void addDonationToDonee() {
//        String doneeId = ui.inputDoneeID();
//        String fullId = "DNE-" + doneeId;
//        Donee donee = doneeMap.get(fullId);
//
//        if (donee != null) {
//            // Get available donation types from DonationManagement
//            LinkedHashMapInterface<String, Donation> donationMap = donationManagement.getDonationMap();
//
//            // Check if donationMap is not empty
//            if (donationMap.isEmpty()) {
//                System.out.println("No donations available.");
//                return;
//            }
//
//            // Display available donations
//            System.out.println("Available Donations:");
//            Iterator<String> donationIterator = donationMap.iterator();
//            while (donationIterator.hasNext()) {
//                String donationId = donationIterator.next();
//                Donation donation = donationMap.get(donationId);
//                System.out.printf("ID: %s, Type: %s\n", donation.getDonationId(), donation.getDonationType());
//            }
//
//            // Prompt user to select a donation ID
//            String donationId = ui.inputDonationID(); // Ensure you have a method for this in DoneeManagementUI
//            if (donationMap.get(donationId) != null) {
//                Donation donation = donationMap.get(donationId);
//                if (confirmAction()) {
//                    donee.addDonation(donationId, donation);
//                    System.out.println("Donation added successfully.");
//                } else {
//                    System.out.println("Action cancelled.");
//                }
//            } else {
//                System.out.println("Selected donation ID not found.");
//            }
//        } else {
//            System.out.println("Donee not found.");
//        }
//
//        ConsoleUtils.systemPause();
//        ConsoleUtils.clearScreen();
//    }
    public static void main(String[] args) {
        DoneeManagement app = new DoneeManagement();
        app.start();
    }
}
