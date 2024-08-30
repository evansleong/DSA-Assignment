/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.*;

/**
 *
 * @author evansleong
 */
public class DoneeDataSeeder {

    private LinkedHashMap<String, Donee> doneeMap;

    public DoneeDataSeeder() {
        doneeMap = new LinkedHashMap<>();
        seedData();
    }

    public void seedData() {

        doneeMap.put("DNE-001", new Donee("DNE-001", "John Doe", "016-7618273", "Individual", 30));
        doneeMap.put("DNE-002", new Donee("DNE-002", "Jane Smith", "012-3456789", "Individual", 25));
        doneeMap.put("DNE-003", new Donee("DNE-003", "Charity Org A", "019-2029122", "Organization", null));
        doneeMap.put("DNE-004", new Donee("DNE-004", "Family B", "03-29201991", "Family", null));
        doneeMap.put("DNE-005", new Donee("DNE-005", "John's Bakery", "03-92003948", "Organization", null));
        doneeMap.put("DNE-006", new Donee("DNE-006", "Doe Family", "018-2920912", "Family", null));
        doneeMap.put("DNE-007", new Donee("DNE-007", "Helping Hands", "03-93139834", "Organization", null));
        doneeMap.put("DNE-008", new Donee("DNE-008", "Maria Green", "019-93012921", "Individual", 40));
        doneeMap.put("DNE-009", new Donee("DNE-009", "Smith Family", "012-91210021", "Family", null));
        doneeMap.put("DNE-010", new Donee("DNE-010", "Community Aid", "03-00990121", "Organization", null));
    }

    public LinkedHashMap<String, Donee> getDoneeMap() {
        return doneeMap;
    }
}
