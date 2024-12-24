package com.mocklab.api.domains.mock.services;

import com.mocklab.api.domains.mock.entities.Mock;
import com.mocklab.api.domains.mock.enums.MethodsMock;
import com.mocklab.api.domains.mock.repositories.MockFindRepository;
import com.mocklab.api.domains.mock.repositories.MockRepository;
import com.mocklab.api.shared.exceptions.SchemaPostValidationException;
import org.springframework.stereotype.Service;

@Service
public class MockFindService {

    private MockFindRepository mockFindRepository;

    private MockRepository mockRepo;
    private PostMockService postSrv;

    public MockFindService(MockFindRepository mockFindRepository, MockRepository mockRepo, PostMockService postSrv){
        this.mockFindRepository = mockFindRepository;
        this.mockRepo = mockRepo;
        this.postSrv = postSrv;
    }

    public Mock findMock(String template, String method, String idUser, Object body) throws Exception {

        template = template.substring(42);
        Long idMock = this.mockFindRepository.findMock(idUser, method, template);
        if (idMock <=0){
            throw new Exception("Mock not founded!");
        }
        Mock mock = this.mockRepo.findById(idMock).orElseThrow();

        //TODO Validations do mock
        if (mock.getMethod().equals(MethodsMock.PUT) || mock.getMethod().equals(MethodsMock.POST)){
            try {
                postSrv.validate(body, mock.getPostSchemaRequest());
            } catch (SchemaPostValidationException spve) {
                throw spve;
            }
        }

        return mock;
    }
}
