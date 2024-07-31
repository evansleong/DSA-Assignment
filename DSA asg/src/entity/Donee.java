/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author evansleong
 */
public class Donee {
    private String doneeId;
    private String doneeName;
    private String doneeType;

    public Donee(String doneeId, String doneeName, String doneeType) {
        this.doneeId = doneeId;
        this.doneeName = doneeName;
        this.doneeType = doneeType;
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

    public String getDoneeId() {
        return doneeId;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public String getDoneeType() {
        return doneeType;
    }

    @Override
    public String toString() {
        return "Donee{" + "doneeId=" + doneeId + ", doneeName=" + doneeName + ", doneeType=" + doneeType + '}';
    }
    
}
