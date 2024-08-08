/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.ArrayList;
import java.util.List;
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

    public String addDistributionId() {
        System.out.println("\nEnter distribution ID: ");
        String distributionId = s.nextLine();
        return distributionId.toUpperCase();
    }

    public String addDistributionDate() {
        System.out.println("\nEnter distribution date(YYYY-MM-DD): ");
        String distributionDate = s.nextLine();
        return distributionDate;
    }

    public String addDistributionDetails() {
        String cont = "y";
        int i = 0;
        List<String> detailList = new ArrayList<>();
        while (cont == "y") {
            System.out.println("Enter distribution details: ");
            String details = s.nextLine();
            detailList.add(details);
            System.out.println("\nAdd more items?(y/n) > ");
            String cInput = s.nextLine();
            cont = cInput.toLowerCase();    
        }

        return disDetList(detailList);
    }
    
    private String disDetList(List<String> detailList){
        String outputStr = "";
        for(int i = 0; i < detailList.size(); i++){
            outputStr += detailList.get(i) + "\n" ;
        }
        return outputStr;
    }
    
    public String addDistributionDoneeId(){
        System.out.println("\nEnter donee ID: ");
        String disDoneeId = s.nextLine();
        return disDoneeId.toUpperCase();
    }

//    public static void main(String[] args) {
//        
//    }
}
