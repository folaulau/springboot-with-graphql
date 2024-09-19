package com.folautech.graphql.repository;

import com.folautech.graphql.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
