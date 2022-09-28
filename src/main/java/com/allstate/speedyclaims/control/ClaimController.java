package com.allstate.speedyclaims.control;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.User;
import com.allstate.speedyclaims.domain.data.UserRepository;
import com.allstate.speedyclaims.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/claim")
public class ClaimController {

    @Autowired
    ClaimService claimService;
    @Autowired
    UserRepository userRepository;



    @GetMapping("findByUser")
    public List<Claim> findByUser(User user) {
        return claimService.findClaimsByUser(user);
    }

    @GetMapping("myClaims")
    public List<Claim> findMyClaims() {
        User user = getCurrentUser();
        return claimService.findClaimsByUser(user);
    }

    @GetMapping(value = "/{id}")
    public Claim findById(@PathVariable("id") Integer id) {
        return claimService.findById(id);
    }

    @GetMapping()
    public List<Claim> findAll() {
        return claimService.findAll();
    }

    @PutMapping("/{id}")
    public Claim updateClaim(@PathVariable("id") Integer id, @RequestBody Map<String, String> data) {
        return claimService.updateClaim(id, data);
    }

    @PostMapping
    public Claim add(@RequestBody Claim claim) {
        claim.setUser(getCurrentUser());
        claim.setClaimStatus("NEW");
        claim.setStaffNotes("");
        return claimService.save(claim);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();
        return userRepository.findByUsername(auth.getName());
    }

    @GetMapping("/volume")
    public Map<String, String> getNumberOfClaims() {
        Integer volume = claimService.countClaims();
        Map<String, String> results = new HashMap<>();
        results.put("volume", volume.toString());
        return results;
    }
}