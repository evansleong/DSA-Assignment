 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author evansleong
 */
public class DoneeManagement {

    DoneeManagementUI ui = new DoneeManagementUI();
    LinkedHashMapInterface<String, Donee> doneeMap;

    public DoneeManagement() {
        doneeMap = new LinkedHashMap<>();
        seedData();
    }

    private void seedData() {
        doneeMap.put("DNE-001", new Donee("DNE-001", "John Doe", "555-1234", "Individual"));
        doneeMap.put("DNE-002", new Donee("DNE-002", "Jane Smith", "555-5678", "Individual"));
        doneeMap.put("DNE-003", new Donee("DNE-003", "Charity Org A", "555-9876", "Organization"));
        doneeMap.put("DNE-004", new Donee("DNE-004", "Family B", "555-4321", "Family"));
        doneeMap.put("DNE-005", new Donee("DNE-005", "John's Bakery", "555-7654", "Organization"));
        doneeMap.put("DNE-006", new Donee("DNE-006", "Doe Family", "555-2468", "Family"));
        doneeMap.put("DNE-007", new Donee("DNE-007", "Helping Hands", "555-1357", "Organization"));
        doneeMap.put("DNE-008", new Donee("DNE-008", "Maria Green", "555-2468", "Individual"));
        doneeMap.put("DNE-009", new Donee("DNE-009", "Smith Family", "555-3579", "Family"));
        doneeMap.put("DNE-010", new Donee("DNE-010", "Community Aid", "555-0987", "Organization"));
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
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addDonee() {
        String type = ui.inputDoneeType();
        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;
        String name = ui.inputDoneeName();
        String contactInfo = ui.inputContactInfo();

        Donee donee = new Donee(fullId, name, contactInfo, type);
        if (confirmAction()) {
            doneeMap.put(fullId, donee);
            System.out.println("Donee added successfully.");
        } else {
            System.out.println("Action cancelled.");
        }
    }

    private void removeDonee() {
        String id = ui.inputDoneeID();
        String fullId = "DNE-" + id;
        doneeMap.remove(fullId);
        System.out.println("Donee removed successfully.");
    }

    private void clearDonees() {
        if (confirmAction()) {
            doneeMap.clear();
            System.out.println("All donees have been cleared.");
        } else {
            System.out.println("Action cancelled.");
        }
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
    }

    private void listDonees() {
        if (doneeMap.isEmpty()) {
            System.out.println("No donees available.");
        } else {
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
            ui.displayDoneeList(doneeArray);
        }
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
    }

    private void filterDonees() {
        int filterType = ui.inputFilterCriteria();
        switch (filterType) {
            case 1:
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
            default:
                System.out.println("Invalid filter type. Please try again.");
        }
    }

    private void generateReports() {
        int totalDonees = doneeMap.size();
        int individualCount = 0;
        int organizationCount = 0;
        int familyCount = 0;

        Iterator<String> iterator = doneeMap.iterator();
        while (iterator.hasNext()) {
            String fullId = iterator.next();
            Donee donee = doneeMap.get(fullId);
            if (donee != null) {
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
            }
        }

        ui.displaySummaryReport(individualCount, organizationCount, familyCount, totalDonees);
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

    public static void main(String[] args) {
        DoneeManagement app = new DoneeManagement();
        app.start();
    }
}
