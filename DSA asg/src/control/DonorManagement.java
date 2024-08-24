/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;
import java.util.Iterator;
import java.util.List;

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
        String[] expList = new String[]{"Shirt", "Food", "Items"};
        Donation donation1 = new Donation("DN-001", "Not cash", expList.toString(), "Deadpool", "20-8-2024");
        Donation donation2 = new Donation("DN-002", "cash", "RM10000", "Wolverine", "21-8-2024");

        donorMap.put("DNR-001", new Donor("DNR-001", "Deadpool", "government", "0123456789"));
        donorMap.put("DNR-002", new Donor("DNR-002", "Wolverine", "private", "0123456789"));
        donorMap.put("DNR-003", new Donor("DNR-003", "Johnny Flame", "public", "0123456789"));

        Donor donor1 = donorMap.get("DNR-001");
        donor1.addDonation(donation1);
        donor1.addDonation(donation2);

        Donor donor2 = donorMap.get("DNR-002");
        donor2.addDonation(donation2);
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
        ui.close(); // Close the scanner resource when done
    }

    private void addDonor() {
        String id = ui.inputDonorID();
        String name = ui.inputDonorName();
        String type = ui.inputDonorType();
        String contact = ui.inputDonorContact();

        Donor donor = new Donor(id, name, type, contact);
        donorMap.put(id, donor);
        ui.displayMessage("Donor added successfully.");
    }

    private void removeDonor() {
        String id = ui.inputDonorID();
        if (donorMap.get(id) != null) {
            donorMap.remove(id);
            ui.displayMessage("Donor removed successfully.");
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

            ui.displayMessage("Donor updated successfully.");
        } else {
            ui.displayError("Donor not found.");
        }
    }

    private void searchDonor() {
        String id = ui.searchDonorID();
        Donor donor = donorMap.get(id);
        if (donor != null) {
            ui.displayMessage(donor.toString());
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
                    List<Donation> donation = donor.getDonation();
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

//    private void sortDonorByDate(){
//        Donor[] donorArray = new Donor[donorMap.size()];
//        Iterator<String> iterator = donorMap.iterator();
//        int i = 0;
//        
//        while(iterator.hasNext()){
//            String id = iterator.next();
//            Donor donor = donorMap.get(id);
//            if(donor!=null){
//                donorArray[i++] = donor;
//            }
//        }
//        
//        LinkedHashMap<String, Donor> map = new LinkedHashMap<>();
//        map.bubbleSort(donorArray, comparator);
//    }
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

        }
    }

//    private Comparator<Donor> donorComparator = new Comparator<Donor>(){
//        @Override
//        public int compare(Donee d1, Donee d2){
//            return 
//        }
//    }
    private void generateSummaryReports() {
        System.out.println("Summary Report:");

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

    public static void main(String[] args) {
        DonorManagement dm = new DonorManagement();
        dm.runSystem();
    }
}
