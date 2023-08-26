package dev.abibou.bookreview.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.abibou.bookreview.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	UserEntity findByUsername(String username);

//	@Query("delete from users where username = :username")
//	void deleteByUsername(String username);

}
