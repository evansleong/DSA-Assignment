/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;

/**
 *
 * @author LEEYIHANG
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

    public int selectDonationCategory() {
        System.out.println("Select the category of donation:");
        System.out.println("1. Food");
        System.out.println("2. Cash");
        System.out.println("3. Daily Necessities");
        System.out.printf("Enter choice > ");
        int categoryChoice = scan.nextInt();
        scan.nextLine(); 
        return categoryChoice;
    }

    public String mgnDonationIDnew() {
        System.out.print("Enter Donation ID > ");
        return scan.nextLine();
    }

    public String mgnDonationtype() {
        System.out.print("Enter Donation Type > ");
        return scan.nextLine();
    }

    public String mgnDonationDetails() {
        System.out.print("Enter Donation Details > ");
        return scan.nextLine();
    }


    public String mgnItemName(String category) {
        System.out.printf("Enter the name of the %s > ", category);
        return scan.nextLine();
    }

    public int mgnQuantity(String category) {
        System.out.printf("Enter the quantity of the %s > ", category);
        int quantity = scan.nextInt();
        scan.nextLine();
        return quantity;
    }

    public double mgnCashAmount() {
        System.out.print("Enter the cash donation amount in RM > ");
        double amount = scan.nextDouble();
        scan.nextLine(); 
        return amount;
    }

    public String inputDonorID() {
        System.out.print("Enter Donor ID > ");
        return scan.nextLine().toUpperCase();
    }

    public String mgnDonationDatenew() {
        System.out.print("Enter Donation Date > ");
        return scan.nextLine();
    }

    public boolean askAddMoreItems() {
        System.out.print("Do you want to add more items? (yes/no): ");
        String response = scan.nextLine().trim().toLowerCase();
        return response.equals("y");
    }

    public void pausescan() {
        scan.close();
    }
    
    public int amendMenu() {
        System.out.println("\n\tDONATION MANAGEMENT SUBSYSTEM\n");
        System.out.println("-------------AMEND MENU--------------");
        System.out.println("\tChoose an operaton(1-3):");
        System.out.println("\t1. Add a new item");
        System.out.println("\t2. Update an existing item");
        System.out.println("\t3. Exit Amend Menu");
        System.out.printf("\nEnter choice > ");
        int mgnAmendChoice = scan.nextInt();
        scan.nextLine(); 
        System.out.println();
        return mgnAmendChoice;
    }


    public String inputItemID() {
        System.out.print("Enter item ID > ");
        return scan.nextLine();
    }

    public int updateItemMenu() {
        System.out.println("\t-----Select the data field-----");
        System.out.println("\t1.Update Amount");
        System.out.println("\t2.Update Description");
        System.out.println("\t");
        return scan.nextInt();
    }
    
    public int mgnfilterMenu(){
        System.out.println("\t-----Select the Filter-----");
        System.out.println("\t 1.Filter by Donation ID");
        System.out.println("\t 2.Filter by Item Amount");
        System.out.println("\t 3.Filter by Description");
        return scan.nextInt();
        
    }

public int mgnFilter(){
        System.out.println("\t-----Select filter criteria-----");
        System.out.println("\t1.");
        System.out.println("\t2.");
        System.out.println("\t");
        return scan.nextInt();
        
    }
    
    public double getInputQuantity() {
        System.out.print("Enter the donation amount/RM  > ");
        double amount = scan.nextDouble();
        scan.nextLine(); 
        return amount;
    }
    
}
