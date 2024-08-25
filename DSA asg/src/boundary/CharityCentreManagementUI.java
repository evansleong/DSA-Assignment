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
public class CharityCentreManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n\t\tCHARITY CENTRE MANAGEMENT SYSTEM");
        System.out.println("\n\t1. Donor Management");
        System.out.println("\t2. Donee management");
        System.out.println("\t3. Donation Management");
        System.out.println("\t0. Exit program");
        System.out.printf("\nEnter choice > ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

}
