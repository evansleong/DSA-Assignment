/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;

/**
 *
 * @author evansleong
 */
public class DonorManagementUI {

    private Scanner scanner;

    public DonorManagementUI() {
        scanner = new Scanner(System.in);
    }

    // Display menu options for donor management
    public int menuOpt() {
        System.out.println("\n\n");
        System.out.println("Donor Management Menu:");
        System.out.println("1. Add Donor");
        System.out.println("2. Remove Donor");
        System.out.println("3. Update Donor Details");
        System.out.println("4. Search Donor Details");
        System.out.println("5. List Donors with Donations");
        System.out.println("6. Filter Donors");
        System.out.println("7. Generate Summary Reports");
        System.out.println("0. Back to main");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice; 
    }

    // Collect information to add a new donor
    public String inputDonorID() {
        System.out.print("Enter Donor ID: ");
        return scanner.nextLine();
    }

    public String inputDonorName() {
        System.out.print("Enter Donor Name: ");
        return scanner.nextLine();
    }

    public String inputDonorType() {
        System.out.print("Enter Donor Type (government/private/public): ");
        return scanner.nextLine();
    }

    public String inputDonorContact() {
        System.out.print("Enter Donor Contact Information: ");
        return scanner.nextLine();
    }

    // Collect information to update a donor
    public String updateDonorID() {
        System.out.print("Enter Donor ID to update: ");
        return scanner.nextLine();
    }

    public String updateDonorName() {
        System.out.print("Enter new Donor Name: ");
        return scanner.nextLine();
    }

    public String updateDonorType() {
        System.out.print("Enter new Donor Type (government/private/public): ");
        return scanner.nextLine();
    }

    public String updateDonorContact() {
        System.out.print("Enter new Donor Contact Information: ");
        return scanner.nextLine();
    }

    // Collect information for searching a donor
    public String searchDonorID() {
        System.out.print("Enter Donor ID to search: ");
//        scanner.nextLine(); // Consume the newline
        return scanner.nextLine();
    }

    // Collect information for filtering donors
    public int inputFilterCriteria() {
        System.out.println("Filter Donors by:");
        System.out.println("1. Donor Type");
        System.out.println("2. Donor Name");
        System.out.println("3. Number of Donations by Donors");
        System.out.print("Enter your filter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    
    public String confirmation(){
        String confirm = "n";
        System.out.print("confirm with y: ");
            confirm = scanner.nextLine();
            return confirm;
    }

    // Method to display messages
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.err.println("Error: " + error);
    }

    public void displayReport(String report) {
        System.out.println("Report:");
        System.out.println(report);
    }

    // Close the scanner resource
    public void close() {
        scanner.close();
    }
}
