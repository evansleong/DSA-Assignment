/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package control;

import adt.*;
import boundary.*;
import dao.DoneeDataSeeder;
import dao.DonorDataSeeder;
import entity.*;
import java.util.Comparator;
import java.util.Iterator;
import utility.ConsoleUtils;

/**
 *
 * @author evansleong
 */
public class DonorManagement {

    DonorManagementUI ui = new DonorManagementUI();
    LinkedHashMapInterface<String, Donor> donorMap;
//    private final DonationManagement donationManagement;
    private final DonorDataSeeder dataSeeder;

    public DonorManagement() {
        donorMap = new LinkedHashMap<>();
//        this.donationManagement = donationManagement;
        dataSeeder = new DonorDataSeeder();
        initializeDonorMap();
//        dummyData();
    }

//    private void dummyData() {
//        List<DonationItem> donationItems = new List<>();
////        String[] expList = new String[]{"Shirt", "Food", "Items"};
//
//        // Create some DonationItem objects
//        DonationItem item1 = new DonationItem("ITEM-001", "Food", 10, "kfc");
//        DonationItem item2 = new DonationItem("ITEM-002", "Clothes", 5, "h&m shirt");
//        DonationItem item3 = new DonationItem("ITEM-003", "Books", 7, "harry porter");
//
//        // Add items to the list
//        donationItems.add(item1);
//        donationItems.add(item2);
//        donationItems.add(item3);
//        // Create donations
//        Donation donation1 = new Donation("DON-001", "DNR-001", donationItems);
//        Donation donation2 = new Donation("DON-002", "DNR-002", donationItems);
//        Donation donation3 = new Donation("DON-003", "DNR-003", donationItems);
//        Donation donation4 = new Donation("DON-004", "DNR-003", donationItems);
//
//        Donor dp = new Donor("Deadpool", "government", "0123456789");
//        Donor wv = new Donor("Wolverine", "private", "0123456789");
//        Donor jf = new Donor("Johnny Flame", "public", "0123456789");
//
//        donorMap.put(dp.getDonorId(), dp);
//        donorMap.put(wv.getDonorId(), wv);
//        donorMap.put(jf.getDonorId(), jf);
//
//        dp.addDonation(donation1);
//        dp.addDonation(donation2);
//        wv.addDonation(donation3);
//        jf.addDonation(donation4);
//    }

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
                    clearDonor();
                    break;
                case 8:
                    generateSummaryReports();
                    break;
                case 0:
                    running = false;
                    ConsoleUtils.clearScreen();
                    break;
                default:
                    ui.displayError("Invalid choice. Please try again.");
            }
        }

    }

    private void addDonor() {
//        String id = ui.inputDonorID();
        System.out.println("");
        String name = ui.inputDonorName();
        String type = null;
        while (type == null) {
            type = typeAssign(ui.inputDonorType());
        }
        String contact = ui.inputDonorContact();

        Donor donor = new Donor(name, type, contact);
        displayDonorDetail(donor);
        donorMap.put(donor.getDonorId(), donor);
        ui.displayMessage("Donor added successfully.");

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void removeDonor() {
        String id = ui.inputDonorID();
        String confirm;
        if (donorMap.get(id) != null) {
            System.out.println("Remove: ");
            displayDonorDetail(donorMap.get(id));
            confirm = ui.confirmation();
            if (confirm.equalsIgnoreCase("y")) {
                donorMap.remove(id);
                ui.displayMessage("Donor removed successfully.");
            } else {
                System.out.println("Donor not removed, thank you");
            }
        } else {
            ui.displayError("Donor not found.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void updateDonor() {
        String id = ui.updateDonorID();
        Donor donor = donorMap.get(id);
        if (donor != null) {
            String name = ui.updateDonorName();
            String type = null;
            while (type == null) {
                type = typeAssign(ui.inputDonorType());
            }
            String contact = ui.updateDonorContact();

            donor.setDonorName(name);
            donor.setDonorType(type);
            donor.setDonorContact(contact);

            ui.displayMessage("Donor updated successfully.\n");
            displayDonorDetail(donor);
        } else {
            ui.displayError("Donor not found.");
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void searchDonor() {
        String id = ui.searchDonorID();
        Donor donor = donorMap.get(id);
        if (donor != null) {
            System.out.printf("\n%-15s %-20s \t%s\n", "ID", "Donor Name", "Donations");
            System.out.println("------------------------------------------------------------");
            ListInterface<Donation> donation = donor.getDonation();
            String toString = "";
            if (!donation.isEmpty()) {
                Iterator<Donation> dIterator = donation.iterator();
                while (dIterator.hasNext()) {
                    toString += dIterator.next().getDonationID() + " ";
                }
            }
            System.out.printf("%-15s %-20s \t%s\n",
                    donor.getDonorId(),
                    donor.getDonorName(),
                    toString
            );
        } else {
            ui.displayError("Donor not found.");
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void clearDonor() {
        if (donorMap.isEmpty()) {
            System.out.println("No existing donors");
        } else {
            System.out.println("Donor List: \n");
            System.out.printf("\n%-15s %-20s %-15s %12s\n", "ID", "Donor Name", "Type", "Contact No.");
            System.out.println("-------------------------------------------------------------------------------------------");

            Iterator<String> iterator = donorMap.iterator();
            while (iterator.hasNext()) {
                String dId = iterator.next();
                Donor donor = donorMap.get(dId);
                if (donor != null) {

                    System.out.printf("%-15s %-20s %-15s %-12s\n",
                            donor.getDonorId(),
                            donor.getDonorName(),
                            donor.getDonorType(),
                            donor.getDonorContact()
                    );
                }
            }
        }

        String confirm = ui.confirmation();
        if (confirm.equalsIgnoreCase("y")) {
            donorMap.clear();
            System.out.println("All donor details cleared.");
        } else {
            System.out.println("Donor details not cleared, thank you.");
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void listDonorsWithDonations() {
        if (donorMap.isEmpty()) {
            System.out.println("No existing donors");
        } else {
            System.out.println("Donor List: \n");
            System.out.printf("\n%-15s %-20s \t%s\n", "ID", "Donor Name", "Donations");
            System.out.println("------------------------------------------------------------");

            Iterator<String> iterator = donorMap.iterator();
            while (iterator.hasNext()) {
                String dId = iterator.next();
                Donor donor = donorMap.get(dId);
                if (donor != null) {
                    ListInterface<Donation> donation = donor.getDonation();
                    String toString = "";
                    if (!donation.isEmpty()) {
                        Iterator<Donation> dIterator = donation.iterator();
                        while (dIterator.hasNext()) {
                            toString += dIterator.next().getDonationID() + " ";
                        }
                    }
                                               
                    System.out.printf("%-15s %-20s \t%s\n",
                            donor.getDonorId(),
                            donor.getDonorName(),
                            toString
                    );
                }
            }
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void sortDonorByName() {
        Donor[] donorArray = new Donor[donorMap.size()];
        Iterator<String> iterator = donorMap.iterator();
        int i = 0;

        while (iterator.hasNext()) {
            String id = iterator.next();
            Donor donor = donorMap.get(id);
            if (donor != null) {
                donorArray[i++] = donor;
            }
        }

        LinkedHashMap<String, Donor> map = new LinkedHashMap<>();
        map.mergeSort(donorArray, donorComparator);

        System.out.printf("\n%-15s %-20s %-15s %12s\n", "ID", "Donor Name", "Type", "Contact No.");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (Donor donor : donorArray) {
            System.out.printf("%-15s %-20s %-15s %-12s\n",
                    donor.getDonorId(),
                    donor.getDonorName(),
                    donor.getDonorType(),
                    donor.getDonorContact()
            );
        }

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();

    }

    private void sortDonorsByNoOfDonation() {
        Donor[] donorArray = new Donor[donorMap.size()];
        Iterator<String> iterator = donorMap.iterator();
        int i = 0;

        while (iterator.hasNext()) {
            String id = iterator.next();
            Donor donor = donorMap.get(id);
            if (donor != null) {
                donorArray[i++] = donor;
            }
        }

        LinkedHashMap<String, Donor> map = new LinkedHashMap<>();
        map.mergeSort(donorArray, donationCountComparator);
        System.out.printf("%-15s %-20s %-15s %12s %-16s\n", "ID", "Donor Name", "Type", "Contact No.", "No. of Donations");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for (Donor donor : donorArray) {
            System.out.printf("%-15s %-20s %-15s %-12s %-16d\n",
                    donor.getDonorId(),
                    donor.getDonorName(),
                    donor.getDonorType(),
                    donor.getDonorContact(),
                    donor.getDonation().size()
            );
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void sortDonorByType() {
        String type = null;
        while (type == null) {
            type = typeAssign(ui.inputDonorType());
        }
        System.out.println("Donor type: " + type);
        Iterator<String> iterator = donorMap.iterator();
        while (iterator.hasNext()) {
            String dId = iterator.next();
            Donor donor = donorMap.get(dId);
            String dType = donor.getDonorType();
            if (donor != null) {
                if (dType.equals(type)) {
                    System.out.printf("%-15s %-20s \t%s\n",
                            donor.getDonorId(),
                            donor.getDonorName(),
                            donor.getDonorType()
                    );
                }
            } else {
                System.out.println("No Donors exist");
            }
        }
        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void filterDonors() {
        int filterType = ui.inputFilterCriteria();
        switch (filterType) {
            case 1:
                sortDonorByType();
                break;
            case 2:
                sortDonorByName();
                break;
            case 3:
                sortDonorsByNoOfDonation();
                break;
        }
    }

    private Comparator<Donor> donorComparator = (Donor d1, Donor d2)
            -> d1.getDonorName().compareToIgnoreCase(d2.getDonorName());

    private Comparator<Donor> donationCountComparator = new Comparator<Donor>() {
        @Override
        public int compare(Donor d1, Donor d2) {
            return Integer.compare(d2.getDonation().size(), d1.getDonation().size());
        }
    };

    private void generateSummaryReports() {
        System.out.println("\nSummary Report:\n");

        int donorSum = donorMap.size();
        int governmentDonor = 0;
        int privateDonor = 0;
        int publicDonor = 0;

        System.out.println("\nTotal number of donors: " + donorSum);

        Iterator<String> iterator = donorMap.iterator();
        while (iterator.hasNext()) {
            String id = iterator.next();
            Donor donor = donorMap.get(id);
            if (donor != null) {
                switch (donor.getDonorType().toLowerCase()) {
                    case "government":
                        governmentDonor++;
                        break;
                    case "private":
                        privateDonor++;
                        break;
                    case "public":
                        publicDonor++;
                        break;
                }
            }
        }
        System.out.println("Number of Government Donors: " + governmentDonor);
        System.out.println("Number of Private Donors: " + privateDonor);
        System.out.println("Number of Public Donors: " + publicDonor);

        ConsoleUtils.systemPause();
        ConsoleUtils.clearScreen();
    }

    private void displayDonorDetail(Donor donor) {
        System.out.println("\nDonor ID: " + donor.getDonorId());
        System.out.println("Donor Name: " + donor.getDonorName());
        System.out.println("Donor Type: " + donor.getDonorType());
        System.out.println("Donor Contact No: " + donor.getDonorContact());
    }

    // Method to check if donor ID exists
    public boolean donorIdExists(String donorId) {
        return donorMap.get(donorId) != null;
    }

    // Method to get donor name by donor ID
    public String getDonorName(String donorId) {
        Donor donor = donorMap.get(donorId);
        if (donor != null) {
            return donor.getDonorName();
        }
        return null; // Return null if donor ID is not found
    }
    
    private void initializeDonorMap(){
        this.donorMap = dataSeeder.getDonorMap();
    }

    private String typeAssign(String type) {
        switch (type.toLowerCase()) {
            case "a":
                return "government";
            case "b":
                return "private";
            case "c":
                return "public";
            default:
                System.out.println("not an option");
                return null;
        }
    }

    public static void main(String[] args) {
        DonorManagement dm = new DonorManagement();
        DonationManagement donationManagement = new DonationManagement(dm);
        DoneeManagement app = new DoneeManagement(donationManagement);
        dm.runSystem();
    }
}
