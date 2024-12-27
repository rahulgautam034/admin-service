package com.flight_app.repository;

import com.flight_app.entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AuthEntity,Long> {
    AuthEntity findByUserName(String userName);
}
