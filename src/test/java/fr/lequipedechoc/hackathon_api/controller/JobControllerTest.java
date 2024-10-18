package fr.lequipedechoc.hackathon_api.controller;

import fr.lequipedechoc.hackathon_api.service.JobService;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.FranceTravailAccessTokenGenerationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    @BeforeEach
    public void setup() {
        // Initialiser les mocks avant chaque test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetJobsByRomeCode_Success() throws Exception {
        // Arrange
        String romeCode = "M1607";
        String expectedResponse = "{ \"companies\": [ { \"name\": \"LIDL\", \"city\": \"PAGNY-SUR-MOSELLE\" } ] }";
        when(jobService.searchJobsByRomeCode(romeCode)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = jobController.getJobsByRomeCode(romeCode);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testGetJobsByRomeCode_TokenGenerationException() throws Exception {
        // Arrange
        String romeCode = "M1607";
        when(jobService.searchJobsByRomeCode(romeCode)).thenThrow(new FranceTravailAccessTokenGenerationException());

        // Act
        ResponseEntity<String> response = jobController.getJobsByRomeCode(romeCode);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erreur lors de la génération du token d'accès", response.getBody());
    }

    @Test
    public void testGetJobsByRomeCode_GenericException() throws Exception {
        // Arrange
        String romeCode = "M1607";
        when(jobService.searchJobsByRomeCode(romeCode)).thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<String> response = jobController.getJobsByRomeCode(romeCode);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erreur lors de la récupération des offres d'emploi", response.getBody());
    }
}
