package fr.lequipedechoc.hackathon_api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lequipedechoc.hackathon_api.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     * @author T.NGUYEN
     */
    Optional<User> findByEmail(String email);
}
