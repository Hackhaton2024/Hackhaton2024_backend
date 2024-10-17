package fr.lequipedechoc.hackathon_api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import fr.lequipedechoc.hackathon_api.cross_cutting.constants.FranceTravailUrlAPI;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.FranceTravailAccessTokenGenerationException;
import fr.lequipedechoc.hackathon_api.service.JobsCodeService;
import java.util.Map;
import java.util.HashMap;

@Service
public class JobsCodeServiceImpl implements JobsCodeService {

    @Autowired
    private RestTemplate restTemplate;

    public JobsCodeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // @Value("${spring.datasource.franceTravailIdClient}")
    private String franceTravailIdClient = "PAR_hackathon_1d0a1fd538207c226f0fcf1e79ee5c3768f7df9947f195b8689116659a450e2e";

    // @Value("${spring.datasource.franceTravailIdClient}")
    private String franceTravailSecretKey = "5ff6ced7f38e38ad3601ecb747e7f32e66e4e623dc3dbc0ce9a4dc8040a58017";

    @Override
    public String getJobsCode(String stringlifiedSearch) {
        ResponseEntity<String> response = restTemplate
                .getForEntity(FranceTravailUrlAPI.GENERATE_FRANCE_TRAVAIL_ACCESS_TOKEN, String.class);
        return response.getBody();
    }

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
    public ResponseEntity<String> generateFranceTravailAccessToken() throws FranceTravailAccessTokenGenerationException {
        RestTemplate restTemplate = new RestTemplate();
       

        String urlTemplate = "https://entreprise.francetravail.fr/connexion/oauth2/access_token?realm=/partenaire";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", "PAR_hackathon_1d0a1fd538207c226f0fcf1e79ee5c3768f7df9947f195b8689116659a450e2e");
        params.add("client_secret", "5ff6ced7f38e38ad3601ecb747e7f32e66e4e623dc3dbc0ce9a4dc8040a58017");
        params.add("scope", "api_offresdemploiv2");
        

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlTemplate);
        builder.queryParams(params);

        String finalUrl = builder.toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>(finalUrl, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(finalUrl, entity, String.class);
        return response;

        // MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // params.add("grant_type", "client_credentials");
        // params.add("client_id",
        // "PAR_hackathon_1d0a1fd538207c226f0fcf1e79ee5c3768f7df9947f195b8689116659a450e2e");
        // params.add("client_secret",
        // "5ff6ced7f38e38ad3601ecb747e7f32e66e4e623dc3dbc0ce9a4dc8040a58017");
        // params.add("scope", "api_offresdemploiv2 api_romeov2 o2dsoffre");

        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params,
        // headers);
        // ResponseEntity<String> response=null;
        // RestTemplate restTemplate2 = new RestTemplate();
        // try{
        // response =
        // restTemplate2.postForEntity("https://entreprise.francetravail.fr/connexion/oauth2/access_token?realm=%2Fpartenaire",
        // request,
        // String.class);

        // }catch(RestClientException ex){
        // System.out.println(ex.getMessage());
        // }

        // if(response.getStatusCode()!=HttpStatus.OK){
        // throw new FranceTravailAccessTokenGenerationException();
        // }

        // return response.getBody();

    }

    // return restTemplate.postForEntity(encodedParams,null,String.class);

    // MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    // formData.add("grant_type", "client_credentials");
    // formData.add("client_id",
    // "PAR_hackathon_1d0a1fd538207c226f0fcf1e79ee5c3768f7df9947f195b8689116659a450e2e");
    // formData.add("client_secret",
    // "5ff6ced7f38e38ad3601ecb747e7f32e66e4e623dc3dbc0ce9a4dc8040a58017");
    // formData.add("scope", "api_offresdemploiv2 api_romeov2 o2dsoffre");

    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // HttpEntity<MultiValueMap<String, String>> request = new
    // HttpEntity<>(formData, headers);

    // ResponseEntity<String> response=null;
    // RestTemplate restTemplate2 = new RestTemplate();
    // try{
    // response =
    // restTemplate2.postForEntity("https://entreprise.francetravail.fr/connexion/oauth2/access_token?realm=%2Fpartenaire",
    // request,
    // String.class);

    // }catch(RestClientException ex){
    // System.out.println(ex.getMessage());
    // }

    // if(response.getStatusCode()!=HttpStatus.OK){
    // throw new FranceTravailAccessTokenGenerationException();
    // }

    // return response.getBody();
}
