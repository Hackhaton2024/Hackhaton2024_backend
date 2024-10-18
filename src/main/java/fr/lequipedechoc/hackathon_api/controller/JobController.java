package fr.lequipedechoc.hackathon_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.lequipedechoc.hackathon_api.service.JobService;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.FranceTravailAccessTokenGenerationException;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Endpoint pour récupérer les offres d'emploi selon un code ROME
    @GetMapping("/rome/{romeCode}")
    public ResponseEntity<String> getJobsByRomeCode(@PathVariable String romeCode) {
        try {
            // Appel du service pour récupérer les offres d'emploi
            String jobOffers = jobService.searchJobsByRomeCode(romeCode);
            return new ResponseEntity<>(jobOffers, HttpStatus.OK);
        } catch (FranceTravailAccessTokenGenerationException e) {
            // Gérer les exceptions liées à la génération du token d'accès
            return new ResponseEntity<>("Erreur lors de la génération du token d'accès", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Gérer les autres exceptions
            return new ResponseEntity<>("Erreur lors de la récupération des offres d'emploi", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
