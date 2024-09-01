/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;
import utility.*;

/**
 *
 * @author Leong Gao Chong
 */
public class CharityCentreManagement {

    CharityCentreManagementUI ui = new CharityCentreManagementUI();
    DonorManagement donorManagement = new DonorManagement();
    DonationManagement donationManagement = new DonationManagement(donorManagement);
    DoneeManagement doneeManagement = new DoneeManagement(donationManagement);

    public CharityCentreManagement() {
    }

    public void start(String[] args) {
        ConsoleUtils.clearScreen();
        boolean running = true;
        while (running) {
            int choice = ui.getMenuChoice();
            switch (choice) {
                case 1:
                    donorManagement.runSystem(); // Replace main(args) with instance method
                    break;
                case 2:
                    doneeManagement.start(); // Replace main(args) with instance method
                    break;
                case 3:
                    donationManagement.dmstart(); // Replace main(args) with instance method
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        CharityCentreManagement app = new CharityCentreManagement();
        app.start(args);
    }

}
