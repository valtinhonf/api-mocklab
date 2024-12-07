package com.mocklab.api.domains.mock.repositories;

import com.mocklab.api.domains.mock.entities.Mock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MockFindRepository {

    @PersistenceContext
    EntityManager em;

    public Long findMock(String idUser, String method, String template){
        try{
            log.info("Calling the find_mock function! Parameters: idUser/IdProject: {}, Method: {}, template: {}", idUser, method, template);
            String sql = "select find_mock(:template, :method, :iduser)";
            Query nativeQuery = em.createNativeQuery(sql, Long.class)
                    .setParameter("template", template)
                    .setParameter("method", method)
                    .setParameter("iduser", idUser);

            Long idMockFinded = (Long) nativeQuery.getSingleResult();

            log.info("Id Mock Finded: {}", idMockFinded);

            return idMockFinded;
        } catch (Exception e) {
            log.error("Mock not founded!");
            throw e;
        }
    }

}
