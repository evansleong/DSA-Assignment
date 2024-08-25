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

    private String itemId;
    private String donationId;
    private String itemName;
    private String category;
    private int quantity;

    public DonationItem(String itemId, String donationId, String itemName, String category, int quantity) {
        this.itemId = itemId;
        this.donationId = donationId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDonationId() {
        return donationId;
    }

    public String getCategory() {
        return category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DonationItem{" + "itemId=" + itemId + ", donationId=" + donationId + ", itemName=" + itemName + ", category=" + category + ", quantity=" + quantity + '}';
    }

}
