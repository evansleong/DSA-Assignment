package boundary;

import java.util.Scanner;

/**
 *
 * @author laixianyu
 */
public class DonorManagementUI {

    private Scanner scanner;

    public DonorManagementUI() {
        scanner = new Scanner(System.in);
    }

    public int menuOpt() {
        System.out.println("");
        System.out.println("Donor Management Menu:");
        System.out.println("1. Add Donor");
        System.out.println("2. Remove Donor");
        System.out.println("3. Update Donor Details");
        System.out.println("4. Search Donor Details");
        System.out.println("5. List Donors with Donations");
        System.out.println("6. Filter Donors");
        System.out.println("7. Clear All Donors");
        System.out.println("8. Generate Summary Reports");
        System.out.println("0. Back to main");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public String inputDonorID() {
        String idHead = "DNR-";
        System.out.print("\nEnter Donor ID number: ");
        return idHead + scanner.nextLine();
    }

    public String inputDonorName() {
        System.out.print("Enter Donor Name: ");
        return scanner.nextLine();
    }

    public String inputDonorType() {
        System.out.println("Enter Donor Type");
        System.out.println("a. Government");
        System.out.println("b. Private");
        System.out.println("c. Public");
        System.out.print("Please select type (a, b, c): ");
        return scanner.nextLine();
    }

    public String inputDonorContact() {
        System.out.print("Enter Donor Contact Information: ");
        return scanner.nextLine();
    }

    public String updateDonorID() {
        String idHead = "DNR-";
        System.out.print("\nEnter Donor ID number to update: ");
        return idHead + scanner.nextLine();
    }

    public String updateDonorName() {
        System.out.print("Enter new Donor Name: ");
        return scanner.nextLine();
    }

    public String updateDonorType() {
        System.out.println("Enter new Donor Type");
        System.out.println("a. Government");
        System.out.println("b. Private");
        System.out.println("c. Public");
        System.out.print("Please select type (a, b, c): ");
        return scanner.nextLine();
    }

    public String updateDonorContact() {
        System.out.print("Enter new Donor Contact Information: ");
        return scanner.nextLine();
    }

    public String searchDonorID() {
        String idHead = "DNR-";
        System.out.print("\nEnter Donor ID number to search: ");
        return idHead + scanner.nextLine();
    }

    public int inputFilterCriteria() {
        System.out.println("\nFilter Donors by:");
        System.out.println("1. Donor Type");
        System.out.println("2. Donor Name");
        System.out.println("3. Number of Donations by Donors");
        System.out.print("Enter your filter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public String confirmation() {
        String confirm = "n";
        System.out.print("confirm with y: ");
        confirm = scanner.nextLine();
        return confirm;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.err.println("\nError: " + error);
    }

    public void displayReport(String report) {
        System.out.println("\nReport:");
        System.out.println(report);
    }

    public void close() {
        scanner.close();
    }
}
