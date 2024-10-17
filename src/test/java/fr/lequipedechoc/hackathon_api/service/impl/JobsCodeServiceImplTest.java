package fr.lequipedechoc.hackathon_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.FranceTravailAccessTokenGenerationException;


/**
 * Unit tests of JobsCodeService
 * 
 * @author T.NGUYEN
 * @date 2024-10-17 
 */
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class JobsCodeServiceImplTest {

    @InjectMocks
    private JobsCodeServiceImpl jobsCodeService;

    @Test
    void generateFranceTravailAccessToken_shouldReturnSuccess() throws FranceTravailAccessTokenGenerationException{
        String franceTravailAccessToken = this.jobsCodeService.generateStringlifiedFranceTravailAccessToken();
        assertThat(franceTravailAccessToken).contains("expires_in");
        assertThat(franceTravailAccessToken).contains("token_type");
        assertThat(franceTravailAccessToken).contains("access_token");
        assertThat(franceTravailAccessToken).contains("scope");
    }

}
