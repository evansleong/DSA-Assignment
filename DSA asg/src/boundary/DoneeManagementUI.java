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
//        System.out.println("\t10. Add donations to donee");
        System.out.println("\t0. Back to main");
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

    public Integer inputAge() {
        System.out.print("\nEnter donee age > ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age input. Age must be a number.");
            return null;
        }
    }

    public int inputAgeGroup() {
        int ageGroup = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Select age group to filter:");
            System.out.println("1. KID (1-20)");
            System.out.println("2. TEENAGER (21-34)");
            System.out.println("3. ADULT (35-54)");
            System.out.println("4. SENIOR CITIZEN (55+)\n");
            System.out.print("Enter your choice (1-4): ");

            try {
                ageGroup = Integer.parseInt(scanner.nextLine());

                if (ageGroup >= 1 && ageGroup <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return ageGroup;
    }

    public String inputDoneeType() {
        System.out.println("Select donee type:");
        System.out.println("------------------");
        System.out.println("A. INDIVIDUAL");
        System.out.println("B. ORGANIZATION");
        System.out.println("C. FAMILY");
        System.out.print("Enter your choice (A/B/C) > ");
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
        System.out.println("------------------------------------");
        System.out.println("1. NAME");
        System.out.println("2. CONTACT NUMBER");
        System.out.println("3. TYPE");
        System.out.println("0. EXIT");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int inputFilterCriteria() {
        System.out.println("\nFilter donees by:");
        System.out.println("-------------------");
        System.out.println("1. DONEE TYPE");
        System.out.println("2. DONEE NAME");
        System.out.println("3. DONEE AGE (FOR INDIVIDUAL ONLY)");
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

    public String inputFirstLetterOfDoneeName() {
        System.out.print("\nEnter the first letter of the donee's name: ");
        String input = scanner.nextLine().trim();
        // Ensure input is a single alphabetic character
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Invalid input. Please enter a single alphabetic character.");
            return "";
        }
        return input.toUpperCase();
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

    public String inputDonationID() {
        System.out.print("\nEnter donation ID > DON-");
        String donationID = scanner.nextLine();
        return donationID.toUpperCase();
    }

    public String inputDonationType() {
        System.out.println("Select donation type:");
        System.out.println("a. Food");
        System.out.println("b. Clothing");
        System.out.println("c. Monetary");
        System.out.print("Enter your choice (a/b/c) > ");
        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "a":
                return "Food";
            case "b":
                return "Clothing";
            case "c":
                return "Monetary";
            default:
                System.out.println("Invalid choice. Defaulting to 'Food'.");
                return "Food"; // Default type if input is invalid
        }
    }

    public String inputDonationDescription() {
        System.out.print("\nEnter donation description > ");
        return scanner.nextLine();
    }

    public String inputDonationDate() {
        System.out.print("\nEnter donation date (YYYY-MM-DD) > ");
        return scanner.nextLine();
    }

}
