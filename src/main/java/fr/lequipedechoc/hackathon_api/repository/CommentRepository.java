package fr.lequipedechoc.hackathon_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lequipedechoc.hackathon_api.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
