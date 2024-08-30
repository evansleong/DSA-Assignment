/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;
import java.util.Iterator;

/**
 *
 * @author LEEYIHANG
 */
public class Donation {

    private String donationID;
    private String donorID;
    private List<DonationItem> items;

    public Donation(String donationID, String donorID, List<DonationItem> items) {
        this.donationID = donationID;
        this.donorID = donorID;
        this.items = items;
        
    }

    public void addItem(DonationItem item) {
        items.add(item);
    }

    public String displayItems(Donation donation) {
        if(donation != null){
        Iterator<DonationItem> iterator = items.iterator();  
        System.out.printf("%-15s %-25s %-20s %-10s %n", "ITEM ID", "ITEM TYPE", "DONATION ITEMS","AMOUNT");
        System.out.println("-------------------------------------------------------------------------");
        StringBuilder itemList = new StringBuilder();
        
        while (iterator.hasNext()) {
             DonationItem item = iterator.next();  
             itemList.append(String.format("%-15s %-25s %-20s %-10s %n",
                                  item.getItemID(),
                                  item.getItemType(),
                                  item.getDescription(),
                                  item.getAmount()));

        }
         return itemList.toString();

        }else{
            return "no item to be displayed";
        }
    }

   
    public String getDonationID() {
        return donationID;
    }

    public String getdonorID() {
        return donorID;
    }

    public List<DonationItem> getItems() {
        return items;
    }

    public void setDonationID(String donationID) {
        this.donationID = donationID;
    }

    public void setdonorID(String donorID) {
        this.donorID = donorID;
    }

    public void setItems(List<DonationItem> items) {
        this.items = items;
    }

    public String getDonationType() {
        String donationType = "";
        Iterator<DonationItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            DonationItem item = iterator.next();
            donationType = item.getItemType();
            
        }
        return donationType;
    }

    public DonationItem getItemById(String itemID) {
        DonationItem foundItem = null;
        Iterator<DonationItem> iterator = items.iterator();

        while (iterator.hasNext()) {
            DonationItem item = iterator.next();
            if (item.getItemID().equals(itemID)) {
                foundItem = item;
                break; 
            }
        }

        return foundItem;
    }

    public boolean removeItemById(String itemID) {
        Iterator<DonationItem> iterator = items.iterator();

        while (iterator.hasNext()) {
            DonationItem item = iterator.next();
            if (item.getItemID().equals(itemID)) {
                iterator.remove(); 
                return true; 
            }
        }

        return false; 
    }

}
