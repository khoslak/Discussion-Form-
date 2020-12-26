package ca.sheridancollege.khoslak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.khoslak.beans.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
