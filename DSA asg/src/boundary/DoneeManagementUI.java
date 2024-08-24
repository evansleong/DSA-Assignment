/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.*;
import java.util.Scanner;

/**
 *
 * @author evansleong
 */
public class DoneeManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n\t\tDONEE MANAGEMENT SUBSYSTEM");
        System.out.println("\n\t1. Add a new donee to the system");
        System.out.println("\t2. Remove an existing donee from the system");
        System.out.println("\t3. Clear all donee data");
        System.out.println("\t4. Modify donee details");
        System.out.println("\t5. Search donee details");
        System.out.println("\t6. List donee with all the donations made");
        System.out.println("\t7. Filter donee based on criteria");
        System.out.println("\t8. Sort donee by name");
        System.out.println("\t9. Generate summary reports");
        System.out.println("\t0. Exit program");
        System.out.printf("\nEnter choice > ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String inputDoneeID() {
        System.out.print("\nEnter donee ID > DNE-");
        String doneeID = scanner.nextLine();
        return doneeID.toUpperCase();
    }

    public String inputDoneeName() {
        System.out.print("\nEnter donee name > ");
        String doneeName = scanner.nextLine();
        return doneeName.toUpperCase();
    }

    public String inputDoneeType() {
        System.out.println("Select donee type:");
        System.out.println("a. Individual");
        System.out.println("b. Organization");
        System.out.println("c. Family");
        System.out.print("Enter your choice (a/b/c) > ");
        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "a":
                return "Individual";
            case "b":
                return "Organization";
            case "c":
                return "Family";
            default:
                System.out.println("Invalid choice. Defaulting to 'Individual'.");
                return "Individual"; // Default type if input is invalid
        }
    }

    public String inputContactInfo() {
        System.out.print("\nEnter donee contact information > ");
        return scanner.nextLine();
    }
    
    public int getUpdateFieldChoice() {
        System.out.println("Select the field you want to update:");
        System.out.println("1. Name");
        System.out.println("2. Contact Info");
        System.out.println("3. Type");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }

    public int inputFilterCriteria() {
        System.out.println("\nFilter donees by:");
        System.out.println("1. Donee type");
        System.out.println("2. Donee name");
        System.out.print("\nEnter choice > ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }

    public char confirmAction() {
        System.out.print("\nAre you sure you want to perform this action? (Y/N) > ");
        String choice = scanner.nextLine();
        return Character.toUpperCase(choice.charAt(0));
    }

    public String inputStartDate() {
        System.out.print("\nEnter start date (YYYY-MM-DD) > ");
        return scanner.nextLine();
    }

    public String inputEndDate() {
        System.out.print("\nEnter end date (YYYY-MM-DD) > ");
        return scanner.nextLine();
    }

    public void displayDoneeList(Donee[] donees) {
        System.out.println("Donee List:");
        // Print table header
        System.out.printf("%-15s %-20s %-25s %-15s\n", "ID", "Name", "Contact Info", "Type");
        System.out.println("-----------------------------------------------------------------------------------");

        // Print each donee's details in a formatted way
        for (Donee donee : donees) {
            System.out.printf("%-15s %-20s %-25s %-15s\n",
                    donee.getDoneeId(),
                    donee.getDoneeName(),
                    donee.getDoneeContact(),
                    donee.getDoneeType());
        }
    }

    public void displaySortedDoneeList(Donee[] donees) {
        System.out.println("Sorted Donee List:");
        displayDoneeList(donees);
    }

    public void displaySummaryReport(int individualCount, int organizationCount, int familyCount, int totalDonees) {
        System.out.println("Summary Report:");
        System.out.println("Total Number of Donees: " + totalDonees);
        System.out.println("Individual Donees: " + individualCount);
        System.out.println("Organization Donees: " + organizationCount);
        System.out.println("Family Donees: " + familyCount);
    }
}
