package com.fiec.lpiiiback.models.repositories;

import com.fiec.lpiiiback.models.entities.User;
import com.fiec.lpiiiback.models.entities.UserJuridico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJuridicoRepository extends JpaRepository<UserJuridico, Integer> {
    Optional<UserJuridico> findByEmailAndPassword(String email, String password);
}
