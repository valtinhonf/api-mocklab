package com.mocklab.api.shared.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataBaseFunctions {

    @PersistenceContext
    private EntityManager entityManager;

    public int nextProjectOnOrganization(UUID idpublicApplication) {
        Query nativeQuery = entityManager.createNativeQuery("select coalesce(max(idsequence) + 1, 1) from public.projects");
        return (Integer) nativeQuery.getSingleResult();
    }

    public int nextUserOnOrganization(Integer idOrganization) {
        Query nativeQuery = entityManager.createNativeQuery("select coalesce(max(idsequence) + 1, 1) from guardian.organization_user where idorganization = ?");
        nativeQuery.setParameter(1, idOrganization);
        return (Integer) nativeQuery.getSingleResult();
    }
}
