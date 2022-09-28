package com.allstate.speedyclaims.unitTests;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.User;
import com.allstate.speedyclaims.domain.UserRole;
import com.allstate.speedyclaims.domain.data.ClaimRepository;
import com.allstate.speedyclaims.domain.data.UserRepository;
import com.allstate.speedyclaims.service.ClaimService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class ServiceTests {
    @Autowired
    ClaimService claimService;

    @MockBean
    ClaimRepository claimRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testNumberOfTransactions() {
        List<Claim> claims = new ArrayList<>();
        User user = new User("Tester", "mypass", UserRole.CUSTOMER,
                "Vinod", "", "Maddela");

        claims.add(
                new Claim(user,"Tester","Tester","Tester",
                        "10050",LocalDate.now(),100.00,
                        "Reason",LocalDate.now(),"Desc", "Cat",
                        "Big cat",null,null,
                        null,null,"NEW",""));
        claims.add(new Claim(user,"Tester","Tester","Tester",
                "10050",LocalDate.now(),100.00,
                "Reason",LocalDate.now(),"Desc","Cat",
                "Big cat",null,null,
                null,null,"NEW",""));
        claims.add(new Claim(user,"Tester","Tester","Tester",
                "10050",LocalDate.now(),100.00,
                "Reason",LocalDate.now(),"Desc","Cat",
                "Big cat",null,null,
                null,null,"NEW",""));

        Mockito.when(claimRepository.findAll()).thenReturn(claims);

        int result = claimService.countClaims();
        assertEquals(3, result);
    }
}
