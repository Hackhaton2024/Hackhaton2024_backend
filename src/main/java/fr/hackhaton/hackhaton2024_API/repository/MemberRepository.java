package fr.hackhaton.hackhaton2024_API.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.hackhaton.hackhaton2024_API.entity.Member;

@Repository // Annotation obligatoire qui indique à Spring que c'est un repository
public interface MemberRepository extends CrudRepository<Member,Integer>{

    // Spring Data va fournir les méthodes pour dialoguer avec la base de données

}
