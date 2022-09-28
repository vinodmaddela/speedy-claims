package com.allstate.speedyclaims.integrationTests;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.data.ClaimRepository;
import com.allstate.speedyclaims.domain.data.UserRepository;
import com.allstate.speedyclaims.dto.ClaimDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@AutoConfigureMockMvc
public class ClaimsTesting {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ClaimRepository claimRepository;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @WithMockUser(username="testuser", password="testpassword", roles={"STAFF"})
    public void checkClaimsAreAddedToDatabase() throws Exception {
        ClaimDto newClaimDto = new ClaimDto();
        newClaimDto.setClaimAmount(12000.00);
        newClaimDto.setClaimDate(LocalDate.now());
        newClaimDto.setIncidentDescription("Desc");
        newClaimDto.setClaimReason("Reason");
        newClaimDto.setVehicleModel("YARIS");

        ObjectMapper objectMapper = mapperBuilder.build();
        String json = objectMapper.writeValueAsString(newClaimDto);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        Claim databaseResponse = newClaimDto.toClaim();
        databaseResponse.setId(1);

        Mockito.when(claimRepository.save(any())).thenReturn(databaseResponse);

        ResultActions result = mockMvc.perform(request)
                .andExpect(status().isOk());

        String responseJson = result.andReturn().getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(databaseResponse);
        System.out.println(responseJson);
        System.out.println(expectedJson);
        JsonContentAssert jsonAssert = new JsonContentAssert(Claim.class, expectedJson);
        jsonAssert.isEqualToJson(responseJson);

      /*  ArgumentCaptor<Claim> capturedClaim
                = ArgumentCaptor.forClass(Claim.class);
        Mockito.verify(claimRepository)
                .save(capturedClaim.capture());
        Claim claim = capturedClaim.getValue();
        assertEquals("VAUXHALL", claim.getVehicleMake());*/

    }
}
