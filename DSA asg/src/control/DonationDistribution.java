/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.*;
import entity.*;

/**
 *
 * @author Asus
 */
public class DonationDistribution {

    DonationDistributionUI d = new DonationDistributionUI();
    ListInterface<Distribution> distributionList;
    HashMapInterface<String, Distribution> distributionMap;

    public DonationDistribution() {
        distributionList = new LinkedList<>();
        distributionMap = new HashMap<>();
    }

    public void runSystem() {
        boolean system = true;
        while (system) {
            int choice = d.menuOpt();
            switch (choice) {
                case 1:
                    addDistribution();
                    break;
                case 2:
                    updateDistribution();
                    break;
                case 3:
                    removeDistribution();
                    break;
                case 4:
                    trackDistribution();
                    break;
                case 5:
                    generateDistributionReports();
                    break;
                case 0:
                    system = false;
                    break;
                default:
                    System.out.println("Error, invalid option!");

            }
        }
    }

    private void addDistribution() {
        String dId = d.addDistributionId();
        String dDate = d.addDistributionDate();
        String dDetails = d.addDistributionDetails();
        String dDoneeId = d.addDistributionDoneeId();
//
//        Distribution distribution = new Distribution(
//                dId,
//                dDate,
//                dDetails,
//                dDoneeId
//        );
//        distributionList.add(distribution);
//        distributionMap.put(dId, distribution);
//        System.out.println("Distribution successfully added");
    }
    
    private void removeDistribution(){
//        String Id = d.addDistributionId();
//        distributionMap.remove(Id);
//        System.out.println("Distribution removed successfully");
    }
    
    private void updateDistribution(){
        String dId = d.addDistributionId();
//        String dDate = d.addDistributionDate();
//        String dDetails = d.addDistributionDetails();
//        String dDoneeId = d.addDistributionDoneeId();
    }
    
    private void trackDistribution(){
        String dId = d.addDistributionId();
    }
    
    private void generateDistributionReports(){
        
    }
    
    public static void main(String[] args) {
        DonationDistribution dd = new DonationDistribution();
        dd.runSystem();
    }
    
}
