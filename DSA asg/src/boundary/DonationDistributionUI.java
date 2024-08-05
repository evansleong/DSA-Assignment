/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class DonationDistributionUI {

    Scanner s = new Scanner(System.in);

    public int menuOpt() {
        System.out.println("\n\t\tDONATION DISTRIBUTION SUBSYSTEM");
        System.out.println("\n\t1. Add distribution");
        System.out.println("\n\t2. Update distribution details");
        System.out.println("\n\t3. Remove distribution");
        System.out.println("\n\t4. Track distributed items");
        System.out.println("\n\t5. Generate summary report");
        System.out.println("\n\t0. Exit program");
        System.out.println("\nEnter choice > ");
        int opt = s.nextInt();
        s.nextLine();
        System.out.println();
        return opt;
    }

    public String addDistribution() {
        System.out.println("\nEnter distribution ID: ");
        String distributionId = s.nextLine();
        return distributionId.toUpperCase();
    }

    public String DistributionDate() {
        System.out.println("\nEnter distribution date(dd-mm-yyyy): ");
        String distributionDate = s.nextLine();
        return distributionDate;
    }

    public String DistributionDetails() {
        String cont = "y";
        int i = 0;
        ArrayList<String> disDet = 
        while (cont == "y") {
            System.out.println("Enter distribution details: ");
            String distributionDetails[] = ;
            return distributionDetails;
        }
    }

}
