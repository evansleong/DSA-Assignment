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
        System.out.println("\t7. Generate summary reports");
        System.out.println("\t0. Exit program");
        System.out.printf("\nEnter choice > ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String inputDoneeID() {
        System.out.print("\nEnter donee ID > ");
        String doneeID = scanner.nextLine();
        return doneeID.toUpperCase();
    }

    public String inputDoneeName() {
        System.out.print("\nEnter donee name > ");
        String doneeName = scanner.nextLine();
        return doneeName.toUpperCase();
    }

    public String inputDoneeType() {
        System.out.print("\nEnter donee type (Individual/Organization/Family) > ");
        String doneeType = scanner.nextLine();
        return doneeType.toUpperCase();
    }

    public String inputContactInfo() {
        System.out.print("\nEnter donee contact information > ");
        String contactInfo = scanner.nextLine();
        return contactInfo;
    }

    public int inputFilterCriteria() {
        System.out.println("\nFilter donees by:");
        System.out.println("1. Donee type");
        System.out.println("2. Donation amount");
        System.out.print("\nEnter choice > ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
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
