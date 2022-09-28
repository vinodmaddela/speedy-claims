package com.allstate.speedyclaims.service;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.User;

import java.util.List;
import java.util.Map;

public interface ClaimService {
    Claim findById(Integer id);
    Claim save(Claim claim);
    List<Claim> findClaimsByUser(User user);
    List<Claim> findAll();
    Claim updateClaim(Integer id, Map<String, String> data);

    Integer countClaims();
}
