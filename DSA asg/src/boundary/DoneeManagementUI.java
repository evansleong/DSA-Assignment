/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.*;
import java.util.Scanner;

/**
 *
 * @author Leong Gao Chong
 */
public class DoneeManagementUI {

    Scanner scanner = new Scanner(System.in);

    public DoneeManagementUI() {

    }

    public int getMenuChoice() {
        System.out.println("\n\t\tDONEE MANAGEMENT SUBSYSTEM");
        System.out.println("\t\t--------------------------");
        System.out.println("\n\t1. Add a new donee to the system");
        System.out.println("\t2. Remove donee(s) from the system");
        System.out.println("\t3. Modify donee details");
        System.out.println("\t4. Search donee details");
        System.out.println("\t5. List donee with all the donations made");
        System.out.println("\t6. Filter donee based on criteria");
        System.out.println("\t7. Sort donee by name");
        System.out.println("\t8. Generate summary reports");
        System.out.println("\t9. Add donations to donee");
        System.out.println("\t0. Back to main");
        System.out.printf("\nEnter choice > ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String inputDoneeID() {
        System.out.print("\nEnter donee ID > DNE-");
        String doneeID = scanner.nextLine();
        return doneeID.toUpperCase();
    }

    public String inputDoneeName() {
        System.out.print("\nEnter donee name > ");
        String doneeName = scanner.nextLine();
        return doneeName.toUpperCase();
    }

    public Integer inputAge() {
        System.out.print("\nEnter donee age > ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age input. Age must be a number.");
            return null;
        }
    }

    public int inputAgeGroup() {
        int ageGroup = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Select age group to filter:");
            System.out.println("---------------------------");
            System.out.println("1. KID (1-20)");
            System.out.println("2. TEENAGER (21-34)");
            System.out.println("3. ADULT (35-54)");
            System.out.println("4. SENIOR CITIZEN (55+)\n");
            System.out.print("\nEnter choice (1-4): ");

            try {
                ageGroup = Integer.parseInt(scanner.nextLine());

                if (ageGroup >= 1 && ageGroup <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return ageGroup;
    }

    public String inputDoneeType() {
        System.out.println("Select donee type:");
        System.out.println("------------------");
        System.out.println("A. INDIVIDUAL");
        System.out.println("B. ORGANIZATION");
        System.out.println("C. FAMILY");
        System.out.print("\nEnter choice (A/B/C) > ");
        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "a":
                return "Individual";
            case "b":
                return "Organization";
            case "c":
                return "Family";
            default:
                System.out.println("Invalid choice. Defaulting to 'Individual'.");
                return "Individual";
        }
    }

    public String inputContactInfo() {
        System.out.print("\nEnter donee contact information > ");
        return scanner.nextLine();
    }

    public int getUpdateFieldChoice() {
        System.out.println("Select the field you want to update:");
        System.out.println("------------------------------------");
        System.out.println("1. NAME");
        System.out.println("2. CONTACT NUMBER");
        System.out.println("3. TYPE");
        System.out.println("0. EXIT");
        System.out.print("\nEnter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int inputFilterCriteria() {
        System.out.println("\nFilter donees by:");
        System.out.println("-------------------");
        System.out.println("1. DONEE TYPE");
        System.out.println("2. DONEE NAME");
        System.out.println("3. DONEE AGE (FOR INDIVIDUAL ONLY)");
        System.out.print("\nEnter choice > ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public char confirmAction() {
        System.out.print("\nAre you sure you want to perform this action? (Y/N) > ");
        String choice = scanner.nextLine();
        return Character.toUpperCase(choice.charAt(0));
    }

    public int promptModifyOneorModifyAll() {
        System.out.println("Choose an option:");
        System.out.println("-----------------");
        System.out.println("1. UPDATE A SPECIFIC FIELD");
        System.out.println("2. UPDATE ALL THE FIELDS AT ONCE");
        System.out.print("\nEnter choice > ");

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return choice;
    }

    public int promptRemoveOrClearDonees() {
        System.out.println("Choose an option:");
        System.out.println("-----------------");
        System.out.println("1. REMOVE A SPECIFIC DONEE");
        System.out.println("2. CLEAR ALL DONEES");
        System.out.print("\nEnter choice > ");

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return choice;
    }

    public int promptAscendingorDescending() {
        System.out.println("Choose an option:");
        System.out.println("-----------------");
        System.out.println("1. ASCENDING (A-Z)");
        System.out.println("2. DESCENDING (Z-A)");
        System.out.print("\nEnter choice > ");

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return choice;
    }

    public int chooseReportType() {
        System.out.println("Choose Report Type:");
        System.out.println("-------------------");
        System.out.println("1. TOTAL DONATIONS MADE TO DONEE BY CATEGORY");
        System.out.println("2. TOP 3 DONEES BY DONATION CATEGORY");
        System.out.print("\nEnter choice > ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void displayDoneeList(Donee[] donees) {
        System.out.println("Donee List:");
        System.out.printf("%-15s %-20s %-25s %-15s\n", "ID", "Name", "Contact Info", "Type");
        System.out.println("-----------------------------------------------------------------------------------");

        for (Donee donee : donees) {
            System.out.printf("%-15s %-20s %-25s %-15s\n",
                    donee.getDoneeId(),
                    donee.getDoneeName(),
                    donee.getDoneeContact(),
                    donee.getDoneeType());
        }
    }

    public void displayDoneeDetails(Donee donee) {
        if (donee == null) {
            System.out.println("Donee not found.");
            return;
        }

        System.out.println("Donee Details:");
        System.out.println("--------------");
        System.out.printf("%-15s: %s\n", "ID", donee.getDoneeId());
        System.out.printf("%-15s: %s\n", "Name", donee.getDoneeName());
        System.out.printf("%-15s: %s\n", "Contact", donee.getDoneeContact());
        System.out.printf("%-15s: %s\n", "Type", donee.getDoneeType());
        System.out.println();
    }

    public String inputFirstLetterOfDoneeName() {
        System.out.print("\nEnter the first letter of the donee's name: ");
        String input = scanner.nextLine().trim();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Invalid input. Please enter a single alphabetic character.");
            return "";
        }
        return input.toUpperCase();
    }

    public void displaySortedDoneeList(Donee[] donees) {
        System.out.println("Sorted Donee List:");
        displayDoneeList(donees);
    }

    public void displayDoneeListHeader() {
        System.out.println("Donees List with Donations:\n");
        System.out.printf("%-15s %-20s %-20s %-20s %20s\n", "DONEE ID", "DONEE NAME", "DONEE TYPE", "CONTACT INFO", "DONATIONS RECEIVED (RM/PCS/SET)");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    public void displayDoneeWithDonations(Donee donee, int foodCount, int dailyNecessitiesCount, double cashCount) {
        System.out.printf("%-15s %-20s %-20s %-20s %-15s\n",
                donee.getDoneeId(),
                donee.getDoneeName(),
                donee.getDoneeType(),
                donee.getDoneeContact(),
                String.format("Food x %d pcs/set\n\t\t\t\t\t\t\t\t\t         Daily Necessities x %d pcs/set\n\t\t\t\t\t\t\t\t\t         Cash x RM %.2f", foodCount, dailyNecessitiesCount, cashCount));
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    public void displayFilteredDoneeHeader(String title, boolean includeAgeGroup, boolean extendedHeader) {
        System.out.println(title);
        if (includeAgeGroup && extendedHeader) {
            System.out.printf("%-15s %-20s %-25s %-15s %-15s\n", "ID", "Name", "Contact Info", "Type", "Age Group");
        } else {
            System.out.printf("%-15s %-20s %-25s %-15s\n", "ID", "Name", "Contact Info", "Type");
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public void displayFilteredDonee(Donee donee, boolean includeAgeGroup, boolean extendedHeader, String... ageGroupLabel) {
        if (includeAgeGroup && extendedHeader && ageGroupLabel.length > 0) {
            System.out.printf("%-15s %-20s %-25s %-15s %-15s\n",
                    donee.getDoneeId(),
                    donee.getDoneeName(),
                    donee.getDoneeContact(),
                    donee.getDoneeType(),
                    ageGroupLabel[0]);
        } else {
            System.out.printf("%-15s %-20s %-25s %-15s\n",
                    donee.getDoneeId(),
                    donee.getDoneeName(),
                    donee.getDoneeContact(),
                    donee.getDoneeType());
        }
    }

    public String getAgeGroupLabel(int ageGroup) {
        switch (ageGroup) {
            case 1:
                return "Kid";
            case 2:
                return "Teenager";
            case 3:
                return "Adult";
            case 4:
                return "Senior Citizen";
            default:
                return null;
        }
    }

    public boolean doesAgeMatchGroup(Integer age, int ageGroup) {
        if (age != null) {
            switch (ageGroup) {
                case 1:
                    return (age >= 1 && age <= 20);
                case 2:
                    return (age >= 21 && age <= 34);
                case 3:
                    return (age >= 35 && age <= 54);
                case 4:
                    return (age >= 55);
            }
        }
        return false;
    }

    public void displaySummaryReport(int individualCount, int organizationCount, int familyCount, double[] individualDonations, double[] organizationDonations, double[] familyDonations, int totalDonees) {

        System.out.println("\n\t\t\t\t\t********** TOTAL DONATION MADE TO DONEE BY CATEGORY **********");
        System.out.println("\t\t\t\t\t************************SUMMARY REPORT************************\n");

        System.out.println("+--------------------------+--------+-----------------+--------------------------+--------------------------+--------------------------+");
        System.out.printf("| %-24s | %-6s | %-15s | %-24s | %-24s | %-24s |\n", "Donee Type", "Number", "Percentage (%)", "Food Donations", "Daily Necessities", "Cash Donations(RM)");
        System.out.println("+--------------------------+--------+-----------------+--------------------------+--------------------------+--------------------------+");

        displayDoneeTypeSummary("Individual Donees", individualCount, individualDonations, totalDonees);
        displayDoneeTypeSummary("Organization Donees", organizationCount, organizationDonations, totalDonees);
        displayDoneeTypeSummary("Family Donees", familyCount, familyDonations, totalDonees);

        System.out.println("+--------------------------+--------+-----------------+--------------------------+--------------------------+--------------------------+\n");

        double totalFood = individualDonations[0] + organizationDonations[0] + familyDonations[0];
        double totalDailyNecessities = individualDonations[1] + organizationDonations[1] + familyDonations[1];
        double totalCash = individualDonations[2] + organizationDonations[2] + familyDonations[2];

        System.out.printf("TOTAL FOOD DONATIONS\t\t\t: %.0f pcs/set\n", totalFood);
        System.out.printf("TOTAL DAILT NECESSITIES DONATIONS\t: %.0f pcs/set\n", totalDailyNecessities);
        System.out.printf("TOTAL CASH DONATIONS\t\t\t: RM %.2f\n", totalCash);

        System.out.println("****************************************************************************************************************************************");
    }

    public void displayTop3DoneesByCategoryReport(
            String[] topFoodDoneeIds, double[] topFoodAmounts,
            String[] topDailyNecessitiesDoneeIds, double[] topDailyNecessitiesAmounts,
            String[] topCashDoneeIds, double[] topCashAmounts) {

        System.out.println("\n\t\t\t********** TOP 3 DONEES BY DONATION CATEGORY **********");
        System.out.println("\t\t\t*******************SUMMARY REPORT**********************\n");

        System.out.println("+-------+-------------------------------+-------------------------------+-------------------------------+");
        System.out.printf("| %-5s | %-29s | %-29s | %-29s |\n", "Rank", "Food Category", "Daily Necessities", "Cash Category");
        System.out.println("+-------+-------------------------------+-------------------------------+-------------------------------+");

        for (int i = 0; i < 3; i++) {
            System.out.printf("| %-5d | %-29s | %-29s | %-29s |\n",
                    (i + 1),
                    formatDoneeInfo(topFoodDoneeIds[i], topFoodAmounts[i], true),
                    formatDoneeInfo(topDailyNecessitiesDoneeIds[i], topDailyNecessitiesAmounts[i], true),
                    formatDoneeInfo(topCashDoneeIds[i], topCashAmounts[i], false)
            );
        }

        System.out.println("+-------+-------------------------------+-------------------------------+-------------------------------+\n");

        System.out.println("*********************************************************************************************************");
    }

    private String formatDoneeInfo(String doneeId, double amount, boolean isInteger) {
        if (doneeId == null) {
            return "N/A";
        }
        if (isInteger) {
            return doneeId + " x " + String.format("%d", (int) amount) + "pcs/set";
        } else {
            return doneeId + " x RM " + String.format("%.2f", amount);
        }
    }

    private void displayDoneeTypeSummary(String doneeType, int doneeCount, double[] donations, int totalDonees) {
        double percentage = (doneeCount / (double) totalDonees) * 100;
        System.out.printf("| %-24s | %6d | %14.2f%% | %24.0f | %24.0f | RM %21.2f |\n", doneeType, doneeCount, percentage, donations[0], donations[1], donations[2]);
    }

    public String inputDonationID() {
        System.out.print("\nEnter donation ID > DON-");
        String donationID = scanner.nextLine();
        return donationID.toUpperCase();
    }

}
