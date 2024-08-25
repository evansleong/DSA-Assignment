/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class DonationManagementUI {

    Scanner scan = new Scanner(System.in);

    public int DonateMngMenu() {
        System.out.println("\n\t\tDONATION MANAGEMENT SUBSYSTEM");
        System.out.println("\n\t1. Add a new donation ");
        System.out.println("\t2. Remove a donation");
        System.out.println("\t3. Search donation details");
        System.out.println("\t4. Amend donation details");
        System.out.println("\t5. Track donated item in categories");
        System.out.println("\t6. List donation by different donor");
        System.out.println("\t7. List all donations");
        System.out.println("\t8. Filter donation based on criteria");
        System.out.println("\t9. Generate summary report");
        System.out.println("\t0. Back to main");
        System.out.printf("\nEnter choice > ");
        int mgnChoice = scan.nextInt();
        scan.nextLine();
        System.out.println();
        return mgnChoice;
    }

    public String mgnItemID() {
        System.out.print("\nEnter Donation ID > ");
        String itemId = scan.nextLine();
        return itemId.toUpperCase();
    }
    
    public String mgnItemName() {
        System.out.print("\nEnter Item Name > ");
        String itemId = scan.nextLine();
        return itemId;
    }
    
    public int mgnQuantity() {
        System.out.print("\nEnter Quantity > ");
        String mgnQuantity = scan.nextLine();
        int quantity = Integer.parseInt(mgnQuantity);
        return quantity;
    }

    public String mgnDonationIDnew() {
        System.out.print("\nEnter Donation ID > ");
        String donationId = scan.nextLine();
        return donationId.toUpperCase();
    }

    public String mgnDonationtype() {
        System.out.print("\nEnter Donation Type > ");
        String donationType = scan.nextLine();
        return donationType;
    }

    public String mgnDonationtypenew() {
        System.out.print("\nEnter Donation Type > ");
        String donationType = scan.nextLine();
        return donationType;
    }

    public String mgnDonationDetails() {
        System.out.print("\nEnter Donation Details > ");
        String donationDetails = scan.nextLine();
        return donationDetails;
    }

    public String mgnDonationDetailsnew() {
        System.out.print("\nEnter Donation Details > ");
        String donationDetails = scan.nextLine();
        return donationDetails;
    }

//    public String mgnDonorName() {
//        System.out.print("\nEnter Donation ID > ");
//        String donorName = scan.nextLine();
//        return donorName;
//    }
    public String mgnDonationDate() {
        System.out.print("\nEnter Donation Date > ");
        String donationDate = scan.nextLine();
        return donationDate;
    }

    public String mgnDonationDatenew() {
        System.out.print("\nEnter Donation Date > ");
        String donationDate = scan.nextLine();
        return donationDate;
    }

    // New method for donor ID input
    public String inputDonorID() {
        System.out.print("\nEnter Donor ID > ");
        String donorId = scan.nextLine();
        return donorId.toUpperCase();
    }

    public void pausescan() {
        scan.close();
    }

    
    public boolean askAddMoreItems() {
        System.out.print("\nDo you want to add another item? (yes/no) > ");
        String response = scan.nextLine();
        return response.equalsIgnoreCase("yes");
    }
    
    
    
}
