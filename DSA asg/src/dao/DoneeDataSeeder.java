/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.*;

/**
 *
 * @author LeongGaoChong
 */
public class DoneeDataSeeder {

    private LinkedHashMap<String, Donee> doneeMap;

    public DoneeDataSeeder() {
        doneeMap = new LinkedHashMap<>();
        seedData();
    }

    public void seedData() {

        doneeMap.put("DNE-001", new Donee("DNE-001", "LAI XIAN YU", "016-7618273", "Individual", 30));
        doneeMap.put("DNE-002", new Donee("DNE-002", "LEE YI HANG", "012-3456789", "Individual", 25));
        doneeMap.put("DNE-003", new Donee("DNE-003", "ABC CHARITY", "019-2029122", "Organization", null));
        doneeMap.put("DNE-004", new Donee("DNE-004", "JOSEPH FAMILIY", "03-29201991", "Family", null));
        doneeMap.put("DNE-005", new Donee("DNE-005", "ILUMINATI", "03-92003948", "Organization", null));
        doneeMap.put("DNE-006", new Donee("DNE-006", "HEW FAMILY", "018-2920912", "Family", null));
        doneeMap.put("DNE-007", new Donee("DNE-007", "LEONG SDN BHD", "03-93139834", "Organization", null));
        doneeMap.put("DNE-008", new Donee("DNE-008", "LEONG KAH YUNG", "019-93012921", "Individual", 40));
        doneeMap.put("DNE-009", new Donee("DNE-009", "LEE FAMILY", "012-91210021", "Family", null));
        doneeMap.put("DNE-010", new Donee("DNE-010", "HEW WEN KYAN", "03-00990121", "Organization", null));
    }

    public LinkedHashMap<String, Donee> getDoneeMap() {
        return doneeMap;
    }
}
