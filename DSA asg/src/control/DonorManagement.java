/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
        dummyData();
    }

    private void dummyData() {
//        String[] expList = new String[]{"Shirt", "Food", "Items"};
        Donation donation1 = new Donation("DON-001", "Clothing", "T-shirts", "Deadpool", "DNR-001");
//        Donation donation2 = new Donation("DN-002", "cash", "RM10000", "Wolverine", "21-8-2024");
        Donation donation2 = new Donation("DON-002", "Food", "Rice and beans", "2024-08-01", "DNR-001");
        Donation donation3 = new Donation("DON-003", "Clothing", "Winter coats", "2024-08-05", "DNR-002");
        Donation donation4 = new Donation("DON-004", "Monetary", "Cash donation", "2024-08-10", "DNR-003");
        Donation donation5 = new Donation("DON-005", "Food", "Canned goods", "2024-08-15", "DNR-001");

        Donor dp = new Donor("Deadpool", "government", "0123456789");
        Donor wv = new Donor("Wolverine", "private", "0123456789");
        Donor jf = new Donor("Johnny Flame", "public", "0123456789");

        donorMap.put(dp.getDonorId(), dp);
        donorMap.put(wv.getDonorId(), wv);
        donorMap.put(jf.getDonorId(), jf);

        dp.addDonation(donation1);
        dp.addDonation(donation2);
        dp.addDonation(donation5);
        wv.addDonation(donation3);
        jf.addDonation(donation4);
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

    }

    private void addDonor() {
//        String id = ui.inputDonorID();
        System.out.println("");
        String name = ui.inputDonorName();
        String type = ui.inputDonorType();
        String contact = ui.inputDonorContact();

        Donor donor = new Donor(name, type, contact);
        displayDonorDetail(donor);
        donorMap.put(donor.getDonorId(), donor);
        ui.displayMessage("Donor added successfully.");
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

            ui.displayMessage("Donor updated successfully.\n");
            displayDonorDetail(donor);
        } else {
            ui.displayError("Donor not found.");
        }
    }

    private void searchDonor() {
        String id = ui.searchDonorID();
        Donor donor = donorMap.get(id);
        if (donor != null) {
            System.out.printf("%-15s %-20s \t%s\n", "ID", "Donor Name", "Donations");
            System.out.println("------------------------------------------------------------");
            ListInterface<Donation> donation = donor.getDonation();
                    String toString = "";
                    if (!donation.isEmpty()) {
                        Iterator<Donation> dIterator = donation.iterator();
                        while (dIterator.hasNext()) {
                            toString += dIterator.next().getDonationId() + " ";
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
    }

    private void listDonorsWithDonations() {
        if (donorMap.isEmpty()) {
            System.out.println("No existing donors");
        } else {
            System.out.println("Donor List: \n");
            System.out.printf("%-15s %-20s \t%s\n", "ID", "Donor Name", "Donations");
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
                            toString += dIterator.next().getDonationId() + " ";
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

        System.out.printf("%-15s %-20s %-15s %12s\n", "ID", "Donor Name", "Type", "Contact No.");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (Donor donor : donorArray) {
            System.out.printf("%-15s %-20s %-15s %-12s\n",
                    donor.getDonorId(),
                    donor.getDonorName(),
                    donor.getDonorType(),
                    donor.getDonorContact()
            );
        }

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
    }
    
    private void filterDonors() {
        int filterType = ui.inputFilterCriteria();
        switch (filterType) {
            case 1:
                String type = ui.inputDonorType();
//                switch{
//                    
//                }
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

        System.out.println("Total number of donors: " + donorSum);

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
    }

    private void displayDonorDetail(Donor donor) {
        System.out.println("\nDonor ID: " + donor.getDonorId());
        System.out.println("Donor Name: " + donor.getDonorName());
        System.out.println("Donor Type: " + donor.getDonorType());
        System.out.println("Donor Contact No: " + donor.getDonorContact());
    }

    public static void main(String[] args) {
        DonorManagement dm = new DonorManagement();
        dm.runSystem();
    }
}
