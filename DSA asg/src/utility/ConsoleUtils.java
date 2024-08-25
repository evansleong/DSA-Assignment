/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 *
 * @author evansleong
 */
public class ConsoleUtils {

    /**
     * Clears the console screen using Robot to simulate key presses. This may
     * not work in all environments and is generally less reliable.
     */
    public static void clearScreen() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(50);  // Set delay to ensure key presses are registered
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException ex) {
            System.err.println("Failed to clear screen using Robot: " + ex.getMessage());
        }
    }
    
    public static void systemPause() {
        System.out.println("\nPress Enter To Continue...");
        new Scanner(System.in).nextLine();
    }
}
