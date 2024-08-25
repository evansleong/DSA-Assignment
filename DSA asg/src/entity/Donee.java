/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;
import java.io.Serializable;

/**
 *
 * @author evansleong
 */
public class Donee implements Serializable {

    private String doneeId;
    private String doneeName;
    private String doneeType;
    private String doneeContact;
    private ListInterface<Donation> donations;

    public Donee(String doneeId, String doneeName, String doneeContact, String doneeType) {
        this.doneeId = doneeId;
        this.doneeName = doneeName;
        this.doneeType = doneeType;
        this.doneeContact = doneeContact;
        this.donations = new List<>();
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

    public void setDonations(ListInterface<Donation> donations) {
        this.donations = donations;
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

    public ListInterface<Donation> getDonations() {
        return donations;
    }

    @Override
    public String toString() {
        return "Donee{" + "doneeId=" + doneeId + ", doneeName=" + doneeName + ", doneeType=" + doneeType + ", doneeContact=" + doneeContact + ", donations=" + donations + '}';
    }

}
