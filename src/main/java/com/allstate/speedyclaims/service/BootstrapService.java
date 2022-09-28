package com.allstate.speedyclaims.service;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.data.ClaimRepository;
import com.allstate.speedyclaims.domain.data.UserRepository;
import com.allstate.speedyclaims.domain.User;
import com.allstate.speedyclaims.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class BootstrapService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserManagementService userManagementService;

    @PostConstruct
    public void createInitialUsers () {
        String username1 = "user1";

        if (userRepository.findAll().size() == 0) {
            User user1 = new User(username1, "mypass", UserRole.CUSTOMER,
                    "Vinod", "", "Maddela");
            User user2 = new User("user2", "mypass", UserRole.STAFF,
                    "Matt", "", "Thornfield");

            userManagementService.save(user1);
            userManagementService.save(user2);
        }
    }
}
