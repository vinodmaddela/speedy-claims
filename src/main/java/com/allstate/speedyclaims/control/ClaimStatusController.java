package com.allstate.speedyclaims.control;

import com.allstate.speedyclaims.domain.ClaimStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/claimstatus")
public class ClaimStatusController {
    @GetMapping
    public List<ClaimStatus> findAll()
    {
        return Arrays.asList(ClaimStatus.values());
    }
}
