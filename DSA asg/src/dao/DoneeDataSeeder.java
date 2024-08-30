/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.*;

/**
 *
 * @author evansleong
 */
public class DoneeDataSeeder {

    private LinkedHashMap<String, Donee> doneeMap;

    public DoneeDataSeeder() {
        doneeMap = new LinkedHashMap<>();
        seedData();
    }

    public void seedData() {
        List<DonationItem> donationItems = new List<>();
        // Create donees
        Donee johnDoe = new Donee("DNE-001", "John Doe", "016-7618273", "Individual", 30); // Example age
        Donee janeSmith = new Donee("DNE-002", "Jane Smith", "012-3456789", "Individual", 25); // Example age
        Donee charityOrgA = new Donee("DNE-003", "Charity Org A", "019-2029122", "Organization", null);
        Donee familyB = new Donee("DNE-004", "Family B", "03-29201991", "Family", null);
        Donee johnsBakery = new Donee("DNE-005", "John's Bakery", "03-92003948", "Organization", null);
        Donee doeFamily = new Donee("DNE-006", "Doe Family", "018-2920912", "Family", null);
        Donee helpingHands = new Donee("DNE-007", "Helping Hands", "03-93139834", "Organization", null);
        Donee mariaGreen = new Donee("DNE-008", "Maria Green", "019-93012921", "Individual", 40); // Example age
        Donee smithFamily = new Donee("DNE-009", "Smith Family", "012-91210021", "Family", null);
        Donee communityAid = new Donee("DNE-010", "Community Aid", "03-00990121", "Organization", null);

        // Add donees to the map
        doneeMap.put(johnDoe.getDoneeId(), johnDoe);
        doneeMap.put(janeSmith.getDoneeId(), janeSmith);
        doneeMap.put(charityOrgA.getDoneeId(), charityOrgA);
        doneeMap.put(familyB.getDoneeId(), familyB);
        doneeMap.put(johnsBakery.getDoneeId(), johnsBakery);
        doneeMap.put(doeFamily.getDoneeId(), doeFamily);
        doneeMap.put(helpingHands.getDoneeId(), helpingHands);
        doneeMap.put(mariaGreen.getDoneeId(), mariaGreen);
        doneeMap.put(smithFamily.getDoneeId(), smithFamily);
        doneeMap.put(communityAid.getDoneeId(), communityAid);

        // Create some DonationItem objects
        DonationItem item1 = new DonationItem("ITEM-001", "Food", 10, "kfc");
        DonationItem item2 = new DonationItem("ITEM-002", "Clothes", 5, "h&m shirt");
        DonationItem item3 = new DonationItem("ITEM-003", "Books", 7, "harry porter");

        // Add items to the list
        donationItems.add(item1);
        donationItems.add(item2);
        donationItems.add(item3);

        // Create donations
        Donation donation1 = new Donation("DON-001", "DNR-001", donationItems);
        Donation donation2 = new Donation("DON-002", "DNR-002", donationItems);
        Donation donation3 = new Donation("DON-003", "DNR-003", donationItems);
        Donation donation4 = new Donation("DON-004", "DNR-003", donationItems);

        // Assuming you have Donor objects like johnDoe, janeSmith, etc.
        johnDoe.addDonation(donation1.getDonationID(), donation1);  // John Doe donates donation1
        janeSmith.addDonation(donation3.getDonationID(), donation3);  // Jane Smith donates donation3
        janeSmith.addDonation(donation4.getDonationID(), donation4);  // Jane Smith donates donation4
        charityOrgA.addDonation(donation3.getDonationID(), donation3);  // CharityOrgA donates donation3
        familyB.addDonation(donation4.getDonationID(), donation4);  // FamilyB donates donation4
        johnsBakery.addDonation(donation1.getDonationID(), donation1);  // JohnsBakery donates donation1
        doeFamily.addDonation(donation2.getDonationID(), donation2);  // DoeFamily donates donation2
        helpingHands.addDonation(donation3.getDonationID(), donation3);  // HelpingHands donates donation3
        mariaGreen.addDonation(donation4.getDonationID(), donation4);  // MariaGreen donates donation4
        smithFamily.addDonation(donation1.getDonationID(), donation1);  // SmithFamily donates donation1
        communityAid.addDonation(donation3.getDonationID(), donation3);
    }
    
    public LinkedHashMap<String, Donee> getDoneeMap() {
        return doneeMap;
    }
}
