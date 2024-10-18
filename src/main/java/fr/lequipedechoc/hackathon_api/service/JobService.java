package fr.lequipedechoc.hackathon_api.service;

import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.FranceTravailAccessTokenGenerationException;
import fr.lequipedechoc.hackathon_api.service.impl.JobsCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobService {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private JobsCodeServiceImpl jobsCodeServiceImpl;

    private static final String BASE_URL = "https://api.francetravail.io/partenaire/offresdemploi/v2/offres/search";

    public String searchJobsByRomeCode(String romeCode) throws FranceTravailAccessTokenGenerationException {

        RestTemplate restTemplate= new RestTemplate();
        // Récupérer l'access token
        String accessToken = jobsCodeServiceImpl.generateStringlifiedFranceTravailAccessToken();
        System.out.println(accessToken);
        // Construire l'URL avec les paramètres spécifiques (ici le code ROME)
        String url = String.format("%s?codeROME=%s", BASE_URL, romeCode);

        // Ajouter l'en-tête Authorization avec le Bearer token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);





        // Créer une requête HttpEntity avec les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Faire la requête GET avec les en-têtes et récupérer la réponse
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Vérifier le statut de la réponse
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); // Retourner la réponse JSON
        } else {
            throw new RuntimeException("Erreur lors de la récupération des offres d'emploi");
        }
    }
}

