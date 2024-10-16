package fr.lequipedechoc.hackathon_api.cross_cutting.exceptions;

import fr.lequipedechoc.hackathon_api.cross_cutting.constants.MessagesEn;

public class InoteExpiredRefreshTokenException extends Exception {
    public InoteExpiredRefreshTokenException() {
        super(MessagesEn.TOKEN_ERROR_REFRESH_TOKEN_EXPIRED);
    }
}
