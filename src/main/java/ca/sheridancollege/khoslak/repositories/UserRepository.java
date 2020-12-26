package ca.sheridancollege.khoslak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.khoslak.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

}
