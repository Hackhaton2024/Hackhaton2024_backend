package fr.lequipedechoc.hackathon_api.cross_cutting.exceptions;

import fr.lequipedechoc.hackathon_api.cross_cutting.constants.MessagesEn;

public class ObtainClosestJobTitleFromFreeTextException extends Exception{
    public ObtainClosestJobTitleFromFreeTextException(){
        super(MessagesEn.ERROR_OCCURED_DURING_OBTAIN_CLOSEST_JOB);
    }
}
