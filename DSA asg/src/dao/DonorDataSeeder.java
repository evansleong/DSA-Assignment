/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.LinkedHashMap;
import adt.LinkedHashMapInterface;
import adt.List;
import entity.Donation;
import entity.DonationItem;
import entity.Donor;

/**
 *
 * @author Asus
 */
public class DonorDataSeeder {

    private LinkedHashMap<String, Donor> donorMap;

    public DonorDataSeeder() {
        donorMap = new LinkedHashMap<>();
        dummyData();
    }

    private void dummyData() {
        List<DonationItem> donationItems = new List<>();
//        String[] expList = new String[]{"Shirt", "Food", "Items"};

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

        Donor dp = new Donor("Deadpool", "government", "0123456789");
        Donor wv = new Donor("Wolverine", "private", "0123456789");
        Donor jf = new Donor("Johnny Flame", "public", "0123456789");
        Donor hm = new Donor("Hamyu","public","01612453728");

        donorMap.put(dp.getDonorId(), dp);
        donorMap.put(wv.getDonorId(), wv);
        donorMap.put(jf.getDonorId(), jf);
        donorMap.put(hm.getDonorId(),hm);

        
        dp.addDonation(donation1);
        dp.addDonation(donation2);
        wv.addDonation(donation3);
        jf.addDonation(donation4);
    }
    
    public LinkedHashMap<String, Donor> getDonorMap() {
        return donorMap;
    }
}
