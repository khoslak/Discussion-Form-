package ca.sheridancollege.khoslak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.khoslak.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	
	public Role findByRolename(String rolename);


}
