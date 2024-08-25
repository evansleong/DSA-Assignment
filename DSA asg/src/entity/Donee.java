/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;

/**
 *
 * @author evansleong
 */
public class Donee {

    private String doneeId;
    private String doneeName;
    private String doneeType;
    private String doneeContact;
    private LinkedHashMap<String, Donation> donations;
    private Integer age;

    public Donee(String doneeId, String doneeName, String doneeContact, String doneeType, Integer age) {
        this.doneeId = doneeId;
        this.doneeName = doneeName;
        this.doneeType = doneeType;
        this.doneeContact = doneeContact;
        this.donations = new LinkedHashMap<>();
        this.age = age;
    }

    public void setDoneeId(String doneeId) {
        this.doneeId = doneeId;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    public void setDoneeType(String doneeType) {
        this.doneeType = doneeType;
    }

    public void setDoneeContact(String doneeContact) {
        this.doneeContact = doneeContact;
    }

    public void setDonations(LinkedHashMap<String, Donation> donations) {
        this.donations = donations;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDoneeId() {
        return doneeId;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public String getDoneeType() {
        return doneeType;
    }

    public String getDoneeContact() {
        return doneeContact;
    }

    public LinkedHashMap<String, Donation> getDonations() {
        return donations;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void addDonation(String donationId, Donation donation) {
        donations.put(donationId, donation); // Use put method of LinkedHashMap
    }
    

    @Override
    public String toString() {
        return "Donee{" + "doneeId=" + doneeId + ", doneeName=" + doneeName + ", doneeType=" + doneeType + ", doneeContact=" + doneeContact + ", donations=" + donations + ", age=" + age + '}';
    }

}
