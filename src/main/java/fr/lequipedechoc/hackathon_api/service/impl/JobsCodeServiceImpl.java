package fr.lequipedechoc.hackathon_api.service.impl;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import fr.lequipedechoc.hackathon_api.cross_cutting.constants.FranceTravail;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.FranceTravailAccessTokenGenerationException;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class JobsCodeServiceImpl{

    // @Value( "${spring.datasource.franceTravailIdClient}" )
    // private String franceTravailIdClient;

    // @Value("${spring.datasource.franceTravailIdClient}")
    // private String franceTravailSecretKey = "5ff6ced7f38e38ad3601ecb747e7f32e66e4e623dc3dbc0ce9a4dc8040a58017";

     /**
     * Generate a France Travail Access token
     *
     * @throws FranceTravailAccessTokenGenerationException
     * 
     * @return Stringlified response if status code is 200
     * 
     * @author T.NGUYEN
     * @date 26-03-2024
     * 
     */
    public String generateStringlifiedFranceTravailAccessToken() throws RestClientException, FranceTravailAccessTokenGenerationException {
       /*
        * RestTemplate est une classe du framework Spring pour Java qui permet de créer des requêtes HTTP pour interagir avec des services RESTful (Representational State of Resource). Elle fournit une interface simple et facile à utiliser pour envoyer des requêtes GET, POST, PUT, DELETE, etc. vers un service RESTful et gérer les réponses.
        * Voici quelques-uns des avantages et des utilisations courantes du RestTemplate :
        * -> Simplification de la création de requêtes HTTP
        *    RestTemplate prend en charge les différents types de requêtes HTTP (GET, POST, PUT, DELETE, etc.) et les headers (Accept, Content-Type, Authorization, etc.) 
        *    ce qui simplifie la création de requêtes complexes.
        * -> Gestion des erreurs
        *    RestTemplate gère les erreurs de manière transparente, vous permettant de récupérer les informations d’erreur 
        *    et de les traiter de manière appropriée.
        * -> Support des formats de données
        *    RestTemplate prend en charge les formats de données couramment utilisés, tels que JSON, XML, et form-urlencoded.
        * -> Interception des requêtes
        *    Vous pouvez ajouter des intercepteurs pour modifier ou ajouter des en-têtes, des corps de requête ou des paramètres avant de envoyer la requête.
        *    Gestion des sessions : RestTemplate prend en charge la gestion des sessions pour les requêtes qui nécessitent une authentification ou une autorisation.
        */ 
        RestTemplate restTemplate = new RestTemplate();
        
        // Creating query parameters
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(FranceTravail.ACCESSTOKEN_PARAM_KEY_GRANTTYPE, FranceTravail.ACCESSTOKEN_PARAM_VALUE_CLIENT_CREDENTIALS);
        params.add(FranceTravail.ACCESSTOKEN_PARAM_KEY_CLIENT_ID, "PAR_hackathon_1d0a1fd538207c226f0fcf1e79ee5c3768f7df9947f195b8689116659a450e2e");
        params.add(FranceTravail.ACCESSTOKEN_PARAM_KEY_CLIENT_SECRET, "5ff6ced7f38e38ad3601ecb747e7f32e66e4e623dc3dbc0ce9a4dc8040a58017");
        params.add(FranceTravail.ACCESSTOKEN_PARAM_KEY_SCOPE, FranceTravail.ACCESSTOKEN_PARAM_VALUE_OFFRESEMPLOIV2);
        
        // Add parameters on url
        String finalUrl = UriComponentsBuilder
                            .fromHttpUrl(FranceTravail.ACCESSTOKEN_URL_GENERATION)
                            .queryParams(params)
                            .toUriString();
        // Add headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> httpEntity = new HttpEntity<>(finalUrl, headers);
        
        // Request Launching
        ResponseEntity<String> response=null;
        try{
            response = restTemplate.postForEntity(finalUrl, httpEntity, String.class);
        }catch(RestClientException ex){
            System.out.printf("%s:%s",
                                ex.getMessage(),
                                ex.getCause());
            throw new FranceTravailAccessTokenGenerationException();
        }

        if(response.getStatusCode()!= HttpStatus.OK){
            throw new FranceTravailAccessTokenGenerationException();
        }

        return response.getBody();
    }
}