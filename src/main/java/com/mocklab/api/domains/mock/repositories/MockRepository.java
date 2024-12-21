package com.mocklab.api.domains.mock.repositories;

import com.mocklab.api.domains.mock.dtos.ProjectionMockProjectDTO;
import com.mocklab.api.domains.mock.entities.Mock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MockRepository extends JpaRepository<Mock, Long> {
    List<Mock> findAllByIduser(UUID uuid);

    Optional<Mock> findByIdmockpublic(UUID uuid);

    @Query(nativeQuery = true,
    value = """
                    select mocks.idmockpublic, mocks.idproject, mocks.iduser, mocks.path, mocks.name,
                           mocks.description, mocks.observation, mocks.method, mocks.status,
                           project.idsequence as projectIdSequence, project.name as projectName,
                           project.description as projectDescription, project.observation as projectObservation
                           from projects project
                           inner join mocks mocks on (mocks.idproject = project.idproject)
                           where project.idorganization = :idorganization
                           order by project.name, mocks.name
                    """)
    List<ProjectionMockProjectDTO> listAllMocksFromOrganizationToHomeGrouped(@Param("idorganization")UUID idOrganization);
}
