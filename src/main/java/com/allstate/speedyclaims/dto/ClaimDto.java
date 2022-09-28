package com.allstate.speedyclaims.dto;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class ClaimDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    User user;
    String firstName;
    String middleName;
    String lastName;
    String policyNumber;
    LocalDate claimDate;
    Double claimAmount;
    String claimReason;
    LocalDate incidentDate;
    String incidentDescription;
    String petAnimal;
    String petBreed;
    String propertyAddress;
    String vehicleMake;
    String vehicleModel;
    Integer vehicleYear;
    String claimStatus;
    String staffNotes;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getStaffNotes() {
        return staffNotes;
    }

    public void setStaffNotes(String staffNotes) {
        this.staffNotes = staffNotes;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public ClaimDto() {
    }

    public ClaimDto(User user, String firstName, String middleName, String lastName,
                    String policyNumber, LocalDate claimDate, Double claimAmount,
                    String claimReason, LocalDate incidentDate, String incidentDescription, String petAnimal,
                    String petBreed, String propertyAddress, String vehicleMake,
                    String vehicleModel, Integer vehicleYear, String claimStatus,
                    String staffNotes) {
        this.user = user;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.policyNumber = policyNumber;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.claimReason = claimReason;
        this.incidentDate = incidentDate;
        this.incidentDescription = incidentDescription;
        this.petAnimal = petAnimal;
        this.petBreed = petBreed;
        this.propertyAddress = propertyAddress;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.claimStatus = claimStatus;
        this.staffNotes = staffNotes;
    }

    public LocalDate getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public String getPetAnimal() {
        return petAnimal;
    }

    public void setPetAnimal(String petAnimal) {
        this.petAnimal = petAnimal;
    }

    public String getPetbreed() {
        return petBreed;
    }

    public void setPetbreed(String petbreed) {
        this.petBreed = petbreed;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public Claim toClaim() {

        Claim claim = new Claim(user, firstName,middleName,lastName,policyNumber,
                claimDate,claimAmount,claimReason,incidentDate,incidentDescription,petAnimal,
                petBreed,propertyAddress,vehicleMake,vehicleModel, vehicleYear,
                claimStatus,staffNotes);

        if (claim.getVehicleModel().toUpperCase().equals("YARIS")) {
            claim.setVehicleMake("TOYOTA");
        }

        return claim;
    }
}
