package fr.lequipedechoc.hackathon_api.cross_cutting.exceptions;

import fr.lequipedechoc.hackathon_api.cross_cutting.constants.MessagesEn;

public class FranceTravailAccessTokenGenerationException extends Exception {
    public FranceTravailAccessTokenGenerationException(){
        super(MessagesEn.ERROR_OCCURED_DURING_ACCESS_TOKEN_GENERATION);
    }

}
