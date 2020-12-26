package ca.sheridancollege.khoslak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.khoslak.beans.Threadiscussion;

public interface ThreadRepository extends JpaRepository<ca.sheridancollege.khoslak.beans.Threadiscussion,Long> {

	public List<Threadiscussion> findAllByOrderByCreateDateDesc();
	public List<Threadiscussion> findAllByOrderByCreateDate();
}
