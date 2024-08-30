/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.LinkedHashMap;
import adt.List;
import entity.Donation;
import entity.DonationItem;
import entity.Donee;

/**
 *
 * @author LEEYIHANG
 */
public class DonationDataSeeder {
      private LinkedHashMap<String, Donation> dmMap;

    public DonationDataSeeder() {
        dmMap = new LinkedHashMap<>();
        seedData();
    }

    public void seedData() {
List<DonationItem> donationItems1 = new List<>();
        List<DonationItem> donationItems2 = new List<>();
        List<DonationItem> donationItems3 = new List<>();
        List<DonationItem> donationItems4 = new List<>();

        
        DonationItem item1 = new DonationItem("ITEM-001", "Food", 10, "Canned Beans");
        DonationItem item2 = new DonationItem("ITEM-002", "Daily Necessities", 5, "Winter Jackets");
        donationItems1.add(item1);
        donationItems1.add(item2);

        
        DonationItem item3 = new DonationItem("ITEM-003", "Daily Necessities", 7, "Children's Books");
        DonationItem item4 = new DonationItem("ITEM-004", "Food", 20, "Rice Bags");
        donationItems2.add(item3);
        donationItems2.add(item4);

        
        DonationItem item5 = new DonationItem("ITEM-005", "Daily Necessities", 15, "Toothpaste");
        DonationItem item6 = new DonationItem("ITEM-006", "Daily Necessities", 10, "T-Shirts");
        donationItems3.add(item5);
        donationItems3.add(item6);

        
        DonationItem item7 = new DonationItem("ITEM-007", "Cash", 666.66, "Cash Donation");
        donationItems4.add(item7);

        Donation donation1 = new Donation("DON-001", "DNR-001", donationItems1);
        Donation donation2 = new Donation("DON-002", "DNR-002", donationItems2);
        Donation donation3 = new Donation("DON-003", "DNR-003", donationItems3);
        Donation donation4 = new Donation("DON-004", "DNR-004", donationItems4);


        dmMap.put(donation1.getDonationID(), donation1);
        dmMap.put(donation2.getDonationID(), donation2);
        dmMap.put(donation3.getDonationID(), donation3);
        dmMap.put(donation4.getDonationID(), donation4);
        
    }

    public LinkedHashMap<String, Donation> getDonationMap() {
        return dmMap;
    }
}
