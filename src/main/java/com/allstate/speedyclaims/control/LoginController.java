package com.allstate.speedyclaims.control;

import com.allstate.speedyclaims.domain.data.UserRepository;
import com.allstate.speedyclaims.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public Map<String,String> login(@RequestBody Map<String,String> loginData) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);

        Map<String,String> response = new HashMap<>();
        response.put("username", username);
        response.put("role", user.getRole().toString());
        return response;
    }

    @GetMapping("getCurrentUser")
    public User getCurrentUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();

        return userRepository.findByUsername(auth.getName());
    }
}
