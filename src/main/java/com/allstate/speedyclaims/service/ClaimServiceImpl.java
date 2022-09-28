package com.allstate.speedyclaims.service;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.User;
import com.allstate.speedyclaims.domain.data.ClaimRepository;
import com.allstate.speedyclaims.domain.data.UserRepository;
import com.allstate.speedyclaims.exceptions.ClaimNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(ClaimService.class);
    @Override
    public List<Claim> findClaimsByUser(User user) {
        return claimRepository.findAllByUser(user);
    }

    @Override
    public List<Claim> findAll() {
        return claimRepository.findAll();
    }

    @Override
    public Claim updateClaim(Integer id, Map<String, String> data) {
        Claim claim = findById(id);
        if (data.containsKey("firstName")) claim.setFirstName(data.get("firstName"));
        if (data.containsKey("middleName")) claim.setMiddleName(data.get("middleName"));
        if (data.containsKey("lastName")) claim.setLastName(data.get("lastName"));
        if (data.containsKey("policyNumber")) claim.setPolicyNumber(data.get("policyNumber"));
        if (data.containsKey("claimDate")) claim.setClaimDate(LocalDate.parse(data.get("claimDate")));
        if (data.containsKey("claimAmount")) claim.setClaimAmount(Double.parseDouble(data.get("claimAmount")));
        if (data.containsKey("claimReason")) claim.setClaimReason(data.get("claimReason"));
        if (data.containsKey("claimStatus")) claim.setClaimStatus(data.get("claimStatus"));
        if (data.containsKey("incidentDate")) claim.setClaimDate(LocalDate.parse(data.get("incidentDate")));
        if (data.containsKey("incidentDescription")) claim.setIncidentDescription(data.get("incidentDescription"));
        if (data.containsKey("petAnimal")) claim.setPetAnimal(data.get("petAnimal"));
        if (data.containsKey("petBreed")) claim.setPetBreed(data.get("petBreed"));
        if (data.containsKey("propertyAddress")) claim.setPropertyAddress(data.get("propertyAddress"));
        if (data.containsKey("vehicleMake")) claim.setVehicleMake(data.get("vehicleMake"));
        if (data.containsKey("vehicleModel")) claim.setVehicleModel(data.get("vehicleModel"));
        if (data.containsKey("vehicleYear")) claim.setVehicleYear(Integer.parseInt(data.get("vehicleYear")));

        //Don't update staff notes if they've already been added
        if(claim.getStaffNotes().equals("") && data.containsKey("staffNotes")) {
            claim.setStaffNotes(data.get("staffNotes"));
        }

        return claimRepository.save(claim);
    }

    @Override
    public Integer countClaims() {
        return claimRepository.findAll().size();
    }

    @Override
    public Claim findById(Integer id) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);

        if (optionalClaim.isPresent()) {
            return optionalClaim.get();
        }

        logger.info("No claim found with id "+id);
        throw new ClaimNotFoundException("There is no claim with an ID of " + id);
    }

    public Claim save(Claim claim) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();

        claim.setUser(userRepository.findByUsername(auth.getName()));
        return claimRepository.save(claim);
    }
}
