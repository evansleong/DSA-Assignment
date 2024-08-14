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
public class DoneeManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n\t\tDONEE MANAGEMENT SUBSYSTEM");
        System.out.println("\n\t1. Add a new donee to the system");
        System.out.println("\t2. Remove an existing donee from the system");
        System.out.println("\t3. Modify donee details");
        System.out.println("\t4. Search donee details");
        System.out.println("\t5. List donee with all the donations made");
        System.out.println("\t6. Filer donee based on criteria");
        System.out.println("\t7. Sort donee by name");
        System.out.println("\t8. Generate summary reports");
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

        String doneeType;
        if (choice.equals("a")) {
            doneeType = "Individual";
        } else if (choice.equals("b")) {
            doneeType = "Organization";
        } else if (choice.equals("c")) {
            doneeType = "Family";
        } else {
            System.out.println("Invalid choice. Defaulting to 'Individual'.");
            doneeType = "Individual"; // Default type if input is invalid
        }

        return doneeType;
    }

    public String inputContactInfo() {
        System.out.print("\nEnter donee contact information > ");
        String contactInfo = scanner.nextLine();
        return contactInfo;
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
}
