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
 * @author evansleong
 */
public class DoneeManagement {

    DoneeManagementUI ui = new DoneeManagementUI();
    HashMapInterface<String, Donee> doneeMap;
    
    public DoneeManagement() {
        doneeMap = new HashMap<>();
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
        String id = ui.inputDoneeID();
        String name = ui.inputDoneeName();
        String contactInfo = ui.inputContactInfo();
        String type = ui.inputDoneeType();

        Donee donee = new Donee(id, name, contactInfo, type);
        if (confirmAction()) {
            doneeMap.put(id, donee);
            System.out.println("Donee added successfully.");
        } else {
            System.out.println("Action cancelled.");
        }
    }

    private void removeDonee() {
        String id = ui.inputDoneeID();
        doneeMap.remove(id);
        System.out.println("Donee removed successfully.");
    }

    private void updateDonee() {
        String id = ui.inputDoneeID();
        Donee donee = doneeMap.get(id);
        if (donee != null) {
            String name = ui.inputDoneeName();
            String contactInfo = ui.inputContactInfo();
            String type = ui.inputDoneeType();

            donee.setDoneeName(name);
            donee.setDoneeContact(contactInfo);
            donee.setDoneeType(type);

            System.out.println("Donee updated successfully.");
        } else {
            System.out.println("Donee not found.");
        }
    }

    private void searchDonee() {
        String id = ui.inputDoneeID();
        Donee donee = doneeMap.get(id);
        if (donee != null) {
            System.out.println("Donee Details:");
            System.out.println("ID: " + donee.getDoneeId());
            System.out.println("Name: " + donee.getDoneeName());
            System.out.println("Contact: " + donee.getDoneeContact());
            System.out.println("Type: " + donee.getDoneeType());
        } else {
            System.out.println("Donee not found.");
        }
    }

    private void listDonees() {
        if (doneeMap.isEmpty()) {
            System.out.println("No donees available.");
        } else {
            System.out.println("Donee List:");
            Iterator<String> iterator = doneeMap.iterator();
            while (iterator.hasNext()) {
                String id = iterator.next();
                Donee donee = doneeMap.get(id);
                if (donee != null) {
                    System.out.println("ID: " + donee.getDoneeId());
                    System.out.println("Name: " + donee.getDoneeName());
                    System.out.println("Contact: " + donee.getDoneeContact());
                    System.out.println("Type: " + donee.getDoneeType());
                    System.out.println("----------------------------");
                }
            }
        }
    }

    private void filterDonees() {
        int filterType = ui.inputFilterCriteria();
        // Implement filtering logic based on the criteria
    }

    private void generateReports() {
        // Implement report generation logic
    }
    
    private boolean confirmAction() {
        char confirmation = ui.confirmAction();
        return confirmation == 'Y';
    }

    public static void main(String[] args) {
        DoneeManagement app = new DoneeManagement();
        app.start();
    }
}
