///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package control;
//
//import adt.*;
//import entity.*;
//import java.util.Scanner;
//
///**
// *
// * @author evansleong
// */
//public class DonationDistributionManagement {
//
//    LinkedHashMapInterface<String, Donee> doneeMap;
//    LinkedHashMapInterface<String, Donee> donationMap;
//    private DoneeManagement doneeManagement;
//    private DonationManagement donationManagement;
//    private Scanner scanner;
//
//    public DonationDistributionManagement(DoneeManagement doneeManagement, DonationManagement donationManagement) {
//        this.doneeManagement = doneeManagement;
//        this.donationManagement = donationManagement;
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void assignDonationsToDonee() {
//        // Step 1: List all available donees
//        System.out.println("Available Donees:");
//        doneeManagement.listDonees();
//
//        // Step 2: Let the user select a donee by ID
//        System.out.print("\nEnter the Donee ID you want to assign donations to > DNE-");
//        String doneeId = "DNE-" + scanner.nextLine().toUpperCase();
//        Donee donee = doneeMap.get(doneeId);
//
//        if (doneeId == null) {
//            System.out.println("Donee not found. Please enter a valid Donee ID.");
//            return;
//        }
//
//        // Step 3: List all available donations
//        System.out.println("Available Donations:");
//        donationManagement.getAllDonations();
//
//        // Step 4: Let the user choose which donation to assign to the donee
//        while (true) {
//            System.out.print("\nEnter the Donation ID to assign to this Donee (or type 'done' to finish) > DON-");
//            String donationIdInput = scanner.nextLine().toUpperCase();
//
//            if (donationIdInput.equalsIgnoreCase("done")) {
//                break;
//            }
//
//            String donationId = "DON-" + donationIdInput;
//            Donation donation = donationMap.get(donationId);
//
//            if (selectedDonation == null) {
//                System.out.println("Donation not found. Please enter a valid Donation ID.");
//                continue;
//            }
//
//            // Step 5: Add the selected donation to the donee
//            selectedDonee.addDonation(donationId, selectedDonation);
//            System.out.println("Donation " + donationId + " has been successfully added to Donee " + selectedDonee.getDoneeName() + ".");
//        }
//    }
//}
