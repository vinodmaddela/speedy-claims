package com.allstate.speedyclaims.unitTests;

import com.allstate.speedyclaims.domain.Claim;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainClassTests {
    @Test
    public void testEqualityForClaims() {
        Claim claim1 = new Claim();
        Claim claim2 = new Claim();

        claim1.setId(123);
        claim2.setId(123);

        assertEquals(claim1.getId(), claim2.getId());
    }

}
