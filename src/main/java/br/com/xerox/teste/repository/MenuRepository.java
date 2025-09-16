package br.com.xerox.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.xerox.teste.domain.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity,Long> {

    @Transactional(readOnly = true)
    Optional<MenuEntity> findByLabelIgnoreCase(String label);
}
