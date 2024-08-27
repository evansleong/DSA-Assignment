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
    DonationManagementUI dmUI = new DonationManagementUI();
    LinkedHashMapInterface<String, Donee> doneeMap;
    private int doneeCounter = 1; // Counter for generating donee IDs
    private DonationManagement donationManagement;

    public DoneeManagement(DonationManagement donationManagement) {
        doneeMap = new LinkedHashMap<>();
        this.donationManagement = donationManagement;
        seedData();
    }

    private void seedData() {

        List<DonationItem> donationItems = new List<>();
        // Create donees
        Donee johnDoe = new Donee("DNE-001", "John Doe", "016-7618273", "Individual", 30); // Example age
        Donee janeSmith = new Donee("DNE-002", "Jane Smith", "012-3456789", "Individual", 25); // Example age
        Donee charityOrgA = new Donee("DNE-003", "Charity Org A", "019-2029122", "Organization", null);
        Donee familyB = new Donee("DNE-004", "Family B", "03-29201991", "Family", null);
        Donee johnsBakery = new Donee("DNE-005", "John's Bakery", "03-92003948", "Organization", null);
        Donee doeFamily = new Donee("DNE-006", "Doe Family", "018-2920912", "Family", null);
        Donee helpingHands = new Donee("DNE-007", "Helping Hands", "03-93139834", "Organization", null);
        Donee mariaGreen = new Donee("DNE-008", "Maria Green", "019-93012921", "Individual", 40); // Example age
        Donee smithFamily = new Donee("DNE-009", "Smith Family", "012-91210021", "Family", null);
        Donee communityAid = new Donee("DNE-010", "Community Aid", "03-00990121", "Organization", null);

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

        // Create some DonationItem objects
        DonationItem item1 = new DonationItem("ITEM-001", "Food", 10, "kfc");
        DonationItem item2 = new DonationItem("ITEM-002", "Clothes", 5, "h&m shirt");
        DonationItem item3 = new DonationItem("ITEM-003", "Books", 7, "harry porter");

        // Add items to the list
        donationItems.add(item1);
        donationItems.add(item2);
        donationItems.add(item3);

        // Create donations
        Donation donation1 = new Donation("DON-001", "DNR-001", donationItems);
        Donation donation2 = new Donation("DON-002", "DNR-002", donationItems);
        Donation donation3 = new Donation("DON-003", "DNR-003", donationItems);
        Donation donation4 = new Donation("DON-004", "DNR-003", donationItems);

// Assuming you have Donor objects like johnDoe, janeSmith, etc.
        johnDoe.addDonation(donation1.getDonationID(), donation1);  // John Doe donates donation1

        janeSmith.addDonation(donation3.getDonationID(), donation3);  // Jane Smith donates donation3
        janeSmith.addDonation(donation4.getDonationID(), donation4);  // Jane Smith donates donation4

        charityOrgA.addDonation(donation3.getDonationID(), donation3);  // CharityOrgA donates donation3

        familyB.addDonation(donation4.getDonationID(), donation4);  // FamilyB donates donation4

        johnsBakery.addDonation(donation1.getDonationID(), donation1);  // JohnsBakery donates donation1

        doeFamily.addDonation(donation2.getDonationID(), donation2);  // DoeFamily donates donation2

        helpingHands.addDonation(donation3.getDonationID(), donation3);  // HelpingHands donates donation3

        mariaGreen.addDonation(donation4.getDonationID(), donation4);  // MariaGreen donates donation4

        smithFamily.addDonation(donation1.getDonationID(), donation1);  // SmithFamily donates donation1

        communityAid.addDonation(donation3.getDonationID(), donation3);
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
                case 10:
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

        if (doneeMap.containsKey(fullId)) {
            Donee donee = doneeMap.get(fullId);

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
            Donee donee = doneeMap.get(fullId);

            boolean updating = true;
            while (updating) {
                int updateChoice = ui.getUpdateFieldChoice();
                switch (updateChoice) {
                    case 1:
                        String name = ui.inputDoneeName();
                        donee.setDoneeName(name);
                        doneeMap.replace(fullId, donee); // Use replace method to update the map
                        System.out.println("Name updated successfully.");
                        break;
                    case 2:
                        String contactInfo = ui.inputContactInfo();
                        donee.setDoneeContact(contactInfo);
                        doneeMap.replace(fullId, donee); // Use replace method to update the map
                        System.out.println("Contact info updated successfully.");
                        break;
                    case 3:
                        String type = ui.inputDoneeType();
                        donee.setDoneeType(type);
                        doneeMap.replace(fullId, donee); // Use replace method to update the map
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

        if (doneeMap.containsKey(fullId)) {
            Donee donee = doneeMap.get(fullId);
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

    public void listDonees() {
        if (checkDoneeIsEmpty()) {
            return;
        }

        // Print the header for donee listing
        System.out.println("Donees List with Donations:");
        System.out.printf("%-15s %-20s %-25s %-15s\n", "DONEE ID", "DONEE NAME", "CONTACT INFO", "DONATIONS RECEIVED");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");

        // Iterate through each donee in the map
        Iterator<String> doneeIterator = doneeMap.iterator();
        while (doneeIterator.hasNext()) {
            String doneeId = doneeIterator.next();
            Donee donee = doneeMap.get(doneeId);

            if (donee != null) {
                // Initialize counters for each donation type
                int foodCount = 0;
                int clothesCount = 0;
                int booksCount = 0;
                int cashCount = 0;

                // Aggregate donation details
                LinkedHashMapInterface<String, Donation> donations = donee.getDonations();
                if (donations != null && !donations.isEmpty()) {
                    Iterator<String> donationIterator = donations.iterator();
                    while (donationIterator.hasNext()) {
                        String donationId = donationIterator.next();
                        Donation donation = donations.get(donationId);
                        if (donation != null) {
                            // Use iterator to loop through items
                            Iterator<DonationItem> itemIterator = donation.getItems().iterator();
                            while (itemIterator.hasNext()) {
                                DonationItem item = itemIterator.next();
                                switch (item.getItemType()) {
                                    case "Food":
                                        foodCount += item.getAmount();
                                        break;
                                    case "Clothes":
                                        clothesCount += item.getAmount();
                                        break;
                                    case "Books":
                                        booksCount += item.getAmount();
                                        break;
                                    case "Cash":
                                        cashCount += item.getAmount();
                                        break;
                                }
                            }
                        }
                    }
                }

                // Print donee details with aggregated donation summary
                System.out.printf("%-15s %-20s %-25s %-15s\n",
                        donee.getDoneeId(),
                        donee.getDoneeName(),
                        donee.getDoneeContact(),
                        String.format("\tFood x %d\n \t\t\t\t\t\t\t\t\tClothes x %d\n \t\t\t\t\t\t\t\t\tBooks x %d\n \t\t\t\t\t\t\t\t\tCash x %d\n-------------------------------------------------------------------------------------------------------------------- ",
                                foodCount, clothesCount, booksCount, cashCount));
            }
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void sortDoneesByName() {
        if (checkDoneeIsEmpty()) {
            return;
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
        doneeMap.mergeSort(doneeArray, doneeNameComparator);
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

                String ageGroupLabel;
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
        if (checkDoneeIsEmpty()) {
            return;
        }

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

    private void displayDoneeList() {
        if (doneeMap.isEmpty()) {
            System.out.println("No donees available.");
            return;
        }

        // Print the header for donee listing
        System.out.printf("%-15s %-20s %-15s\n", "DONEE ID", "DONEE NAME", "CONTACT NO");
        System.out.println("----------------------------------------------");

        // Iterate through each donee in the map
        Iterator<String> iterator = doneeMap.iterator();
        while (iterator.hasNext()) {
            String fullId = iterator.next();
            Donee donee = doneeMap.get(fullId);

            if (donee != null) {
                // Print donee ID, name, and contact number
                System.out.printf("%-15s %-20s %-15s\n",
                        donee.getDoneeId(),
                        donee.getDoneeName(),
                        donee.getDoneeContact());
            }
        }
    }

    private void addDonationToDonee() {
        // Input donee ID
//        String doneeId = ui.inputDoneeID();
        String doneeId = ui.inputDoneeID();
        String fullDoneeId = "DNE-" + doneeId;

        // Check if the donee ID exists in the system
        if (doneeMap.containsKey(fullDoneeId)) {
            // Display available donations
//            List<Donation> availableDonations = donationManagement.getAllDonations();
            Donee donee = doneeMap.get(fullDoneeId);

            // Display the donee's name
            System.out.println("Donee Name: " + donee.getDoneeName());

            // List available donations
            List<Donation> availableDonations = donationManagement.getAllDonations();
            if (availableDonations.isEmpty()) {
                System.out.println("No available donations at the moment.");
                return;
            }

            // Use Iterator to iterate through the donations
            System.out.println("Available Donations:");
            Iterator<Donation> iterator = availableDonations.iterator();
            while (iterator.hasNext()) {
                Donation donation = iterator.next();
                System.out.println("Donation ID: " + donation.getDonationID() + " | Type: " + donation.getDonationType());
            }

            // Input donation ID
            String donationID = dmUI.mgnDonationIDnew();
            String fullDonationId = "DON-" + donationID;

            // Check if the donation ID exists
            if (donationManagement.donationIdExists(fullDonationId)) {
                // Retrieve the donation
                Donation donation = donationManagement.getDonation(fullDonationId);

                donee.addDonation(fullDonationId, donation);

                System.out.println("Donation successfully assigned to Donee.");
            } else {
                System.out.println("Donation ID not found. Please check the ID and try again.");
            }
        } else {
            System.out.println("Donee ID not found in the system. Please check the ID and try again.");
        }
    }

    public static void main(String[] args) {
        DonorManagement donorManagement = new DonorManagement(); // Create an instance of DonorManagement
        DonationManagement donationManagement = new DonationManagement(donorManagement); // Pass it to DonationManagement
        DoneeManagement app = new DoneeManagement(donationManagement); // Pass DonationManagement to DoneeManagement
        app.start(); // Start the DoneeManagement application
    }
}
