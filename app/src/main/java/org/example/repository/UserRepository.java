package org.example.repository;

import org.example.entities.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<UserInfo, String> {
    public Optional<UserInfo> findByUsername(String username);
}
