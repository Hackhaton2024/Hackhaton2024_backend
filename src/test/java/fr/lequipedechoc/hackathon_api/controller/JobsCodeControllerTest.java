package fr.lequipedechoc.hackathon_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.lequipedechoc.hackathon_api.cross_cutting.enums.RoleEnum;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.*;
import fr.lequipedechoc.hackathon_api.cross_cutting.security.impl.JwtServiceImpl;
import fr.lequipedechoc.hackathon_api.entity.Role;
import fr.lequipedechoc.hackathon_api.entity.User;
import fr.lequipedechoc.hackathon_api.service.impl.JobsCodeServiceImpl;
import fr.lequipedechoc.hackathon_api.service.impl.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;

import static fr.lequipedechoc.hackathon_api.ConstantsForTests.*;
import static org.mockito.Mockito.*;

@WebMvcTest(CommentController.class)
/*
 * Enables all autoconfiguration related to MockMvc and ONLY MockMvc + none
 * Spring security filters applied
 */
@AutoConfigureMockMvc(addFilters = false)

/* Add Mockito functionalities to Junit 5 */
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class JobsCodeControllerTest {

   /* DEPENDENCIES INJECTION */
        /* ============================================================ */

        /*
         * MockMvc provides a convenient way to send requests to your application and
         * inspect the
         * responses, allowing you to verify the behavior of your controllers in
         * isolation.
         * -> Need to be autowired to be autoconfigured
         */
        @SuppressWarnings("unused")
        @Autowired
        private MockMvc mockMvc;

        /* ObjectMapper provide functionalities for read and write JSON data's */
        @SuppressWarnings("unused")
        @Autowired
        private ObjectMapper objectMapper;

        /* DEPENDENCIES MOCKING */
        /* ============================================================ */
        @MockBean
        private AuthenticationManager authenticationManager;
        @MockBean
        private JwtServiceImpl jwtServiceImpl;
        @MockBean
        private UserServiceImpl userService;
       
        @MockBean
        private JobsCodeServiceImpl jobsCodeService;


         /* REFERENCES FOR MOCKING */
        /* ============================================================ */
        Role roleForTest = Role.builder().name(RoleEnum.ADMIN).build();

        User userRef = User.builder()
                        .email(REFERENCE_USER_EMAIL)
                        .name(REFERENCE_USER_NAME)
                        .password(REFERENCE_USER_PASSWORD)
                        .role(roleForTest)
                        .build();


    @Test
    void testGetAIresponse_shouldReturn200() throws JsonMappingException, RestClientException, JsonProcessingException, FranceTravailAccessTokenGenerationException {

        String mockedResponse = 
            """
                [
                {
                  "metiersRome": [
                    {
                      "libelleAppellation": "Boucher / Bouchère",
                      "codeAppellation": "11564",
                      "libelleRome": "Boucherie",
                      "codeRome": "D1101",
                      "scorePrediction": 0.75
                    }
                  ],
                  "uuidInference": "bca57776-9e3f-4c72-b939-8a48cd87ff6e",
                  "identifiant": "123456",
                  "intitule": "boucher",
                  "contexte": "Commerce de détail de viandes et de produits à base de viande en magasin spécialisé"
                }
              ]""";

        when(this.jobsCodeService.generateFranceTravailAccessToken()).thenReturn(mockedResponse);

        
    }
}
