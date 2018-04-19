package com.javasampleapproach.restdata.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.javasampleapproach.restdata.model.User;

@RepositoryRestResource(collectionResourceRel = "User", path = "User")
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	List<User> findByLastName(@Param("id") String id);
}
