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
        System.out.println("");
        System.out.println("Donor Management Menu:");
        System.out.println("1. Add Donor");
        System.out.println("2. Remove Donor");
        System.out.println("3. Update Donor Details");
        System.out.println("4. Search Donor Details");
        System.out.println("5. List Donors with Donations");
        System.out.println("6. Filter Donors");
        System.out.println("7. Clear All Donors");
        System.out.println("8. Generate Summary Reports");
        System.out.println("0. Back to main");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice; 
    }

    // Collect information to add a new donor
    public String inputDonorID() {
        System.out.print("\nEnter Donor ID: ");
        return scanner.nextLine();
    }

    public String inputDonorName() {
        System.out.print("Enter Donor Name: ");
        return scanner.nextLine();
    }

    public String inputDonorType() {
        System.out.println("Enter Donor Type");
        System.out.println("a. Government");
        System.out.println("b. Private");
        System.out.println("c. Public");
        System.out.print("Please select type (a, b, c): ");
        return scanner.nextLine();
    }

    public String inputDonorContact() {
        System.out.print("Enter Donor Contact Information: ");
        return scanner.nextLine();
    }

    // Collect information to update a donor
    public String updateDonorID() {
        String idHead = "DNR-";
        System.out.print("\nEnter Donor ID number to update: ");
        return idHead + scanner.nextLine();
    }

    public String updateDonorName() {
        System.out.print("Enter new Donor Name: ");
        return scanner.nextLine();
    }

    public String updateDonorType() {
        System.out.println("Enter new Donor Type");
        System.out.println("a. Government");
        System.out.println("b. Private");
        System.out.println("c. Public");
        System.out.print("Please select type (a, b, c): ");
        return scanner.nextLine();
    }

    public String updateDonorContact() {
        System.out.print("Enter new Donor Contact Information: ");
        return scanner.nextLine();
    }

    // Collect information for searching a donor
    public String searchDonorID() {
        String idHead = "DNR-";
        System.out.print("\nEnter Donor ID number to search: ");
//        scanner.nextLine(); // Consume the newline
        return idHead + scanner.nextLine();
    }

    // Collect information for filtering donors
    public int inputFilterCriteria() {
        System.out.println("\nFilter Donors by:");
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
        System.err.println("\nError: " + error);
    }

    public void displayReport(String report) {
        System.out.println("\nReport:");
        System.out.println(report);
    }

    // Close the scanner resource
    public void close() {
        scanner.close();
    }
}
