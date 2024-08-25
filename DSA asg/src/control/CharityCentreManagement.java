/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;

/**
 *
 * @author evansleong
 */
public class CharityCentreManagement {

    CharityCentreManagementUI ui = new CharityCentreManagementUI();

    public CharityCentreManagement() {
    }
    
    
    public void start(String[] args) {
        boolean running = true;
        while (running) {
            int choice = ui.getMenuChoice();
            switch (choice) {
                case 1:
                    DonorManagement.main(args);
                    break;
                case 2:
                    DoneeManagement.main(args);
                    break;
                case 3:
                    DonationManagement.main(args);
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
