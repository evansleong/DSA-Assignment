/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author evansleong
 */
public class DonationItem {

    private String itemID;
    private String itemType; // "Cash" or "Kind"
    private double amount; // for cash
    private String description; // for kind

    public DonationItem(String itemID, String itemType, double amount, String description) {
        this.itemID = itemID;
        this.itemType = itemType;
        this.amount = amount;
        this.description = description;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemType() {
        return itemType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemID: " + itemID + ", Type: " + itemType + ", Amount: " + amount + ", Description: " + description;
    }
}
