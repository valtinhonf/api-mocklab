package com.mocklab.api.domains.mock.repositories;

import com.mocklab.api.domains.mock.entities.Mock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MockRepository extends JpaRepository<Mock, Long> {
    List<Mock> findAllByIduser(UUID uuid);

    Optional<Mock> findByIdmockpublic(UUID uuid);
}
