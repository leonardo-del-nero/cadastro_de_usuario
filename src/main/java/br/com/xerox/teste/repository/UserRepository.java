package br.com.xerox.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.xerox.teste.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Transactional(readOnly = true)
    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
