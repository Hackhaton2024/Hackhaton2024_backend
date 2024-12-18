package fr.lequipedechoc.hackathon_api.service;

import java.util.List;

import fr.lequipedechoc.hackathon_api.cross_cutting.exceptions.InoteUserException;
import fr.lequipedechoc.hackathon_api.entity.Role;

public interface RoleService {

    /**
     * Populates the role table from the dedicated enumeration
     *
     * @return list of saved roles
     * @author T.NGUYEN
     * @date 28/03/2024
     */
    List<Role> insertRolesInDb();

    /**
     * Load admin role
     *
     * @return Singleton of asked admin Role
     * @author T.NGUYEN
     * @date 28/03/2024
     */
    Role loadAdminRole() throws InoteUserException;

    /**
     * Load Manager role
     *
     * @return Singleton of asked manager Role
     * @author T.NGUYEN
     * @date 28/03/2024
     */
    Role loadManagerRole() throws InoteUserException;

    /**
     * Load User role
     *
     * @return Singleton of asked user Role
     * @author T.NGUYEN
     * @date 28/03/2024
     */
    Role loadUserRole() throws InoteUserException;
}

