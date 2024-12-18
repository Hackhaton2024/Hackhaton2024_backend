package fr.lequipedechoc.hackathon_api.service;

import org.springframework.mail.MailException;

import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.InoteInvalidEmailException;
import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.InoteMailException;
import fr.lequipedechoc.hackathon_api.entity.Validation;

/**
 * The interface Notification service.
 * @author T.NGUYEN
 * @date 26-03-2024
 */
public interface NotificationService {
    /**
     * Send validation by email.
     * @author T.NGUYEN
     *
     * @date 26-03-2024
     * @param validation the validation
     * @throws InoteMailException 
     */
    void sendValidation_byEmail(Validation validation) throws MailException, InoteInvalidEmailException, InoteMailException;

}
