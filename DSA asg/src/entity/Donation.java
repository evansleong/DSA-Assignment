/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;
import java.util.Iterator;

/**
 *
 * @author Asus
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

    public String displayItems() {
        StringBuilder itemList = new StringBuilder();
        Iterator<DonationItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            DonationItem item = iterator.next();
            itemList.append(item.toString()).append("\n");
        }
        return itemList.toString();
    }

    // Other getters and setters as needed
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
            // If mixed types, handle accordingly
        }
        return donationType;
    }

    // Method to get an item by its ID
    public DonationItem getItemById(String itemID) {
        DonationItem foundItem = null;
        Iterator<DonationItem> iterator = items.iterator();

        while (iterator.hasNext()) {
            DonationItem item = iterator.next();
            if (item.getItemID().equals(itemID)) {
                foundItem = item;
                break; // Exit the loop once the item is found
            }
        }

        return foundItem;
    }

    public boolean removeItemById(String itemID) {
        Iterator<DonationItem> iterator = items.iterator();

        while (iterator.hasNext()) {
            DonationItem item = iterator.next();
            if (item.getItemID().equals(itemID)) {
                iterator.remove(); // Remove the item from the list
                return true; // Return true indicating the item was found and removed
            }
        }

        return false; // Return false if the item was not found
    }

}
