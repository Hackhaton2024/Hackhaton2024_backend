package fr.lequipedechoc.hackathon_api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import fr.lequipedechoc.hackathon_api.cross_cutting.enums.RoleEnum;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.FranceTravailAccessTokenGenerationException;
import fr.lequipedechoc.hackathon_api.entity.Role;
import fr.lequipedechoc.hackathon_api.entity.User;
import static fr.lequipedechoc.hackathon_api.ConstantsForTests.*;


/**
 * Unit tests of JobsCodeService
 * 
 * @author T.NGUYEN
 * @date 2024-10-17 
 */
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class JobsCodeServiceImplTest {

    // @Mock
    // private RestTemplate restTemplate = new RestTemplate();

    // public JobsCodeServiceImplTest(RestTemplate restTemplate) {
    //     this.restTemplate = restTemplate;
    // }

    /* DEPENDENCIES INJECTION */
        /* ============================================================ */
        /*
         * @InjectMocks instance class to be tested and automatically inject mock fields
         * Nota : if service is an interface, instanciate implementation withs mocks in
         * params
         */
       

    @InjectMocks
    private JobsCodeServiceImpl jobsCodeService;

    /* REFERENCES FOR MOCKING */
        /* ============================================================ */
        

    @Test
    void generateFranceTravailAccessToken_shouldReturnSuccess() throws FranceTravailAccessTokenGenerationException{
        assertThat((this.jobsCodeService.generateFranceTravailAccessToken())).isNotNull();
    }
}
