package com.mocklab.api.domains.mock.services;

import com.mocklab.api.domains.mock.entities.Mock;
import com.mocklab.api.domains.mock.repositories.MockFindRepository;
import com.mocklab.api.domains.mock.repositories.MockRepository;
import org.springframework.stereotype.Service;

@Service
public class MockFindService {

    private MockFindRepository mockFindRepository;

    private MockRepository mockRepo;

    public MockFindService(MockFindRepository mockFindRepository, MockRepository mockRepo){
        this.mockFindRepository = mockFindRepository;
        this.mockRepo = mockRepo;
    }

    public Mock findMock(String template, String method, String idUser) throws Exception {
        template = template.substring(42);
        Long idMock = this.mockFindRepository.findMock(idUser, method, template);
        if (idMock <=0){
            throw new Exception("Mock not founded!");
        }
        Mock mock = this.mockRepo.findById(idMock).orElseThrow();

        //TODO Validations do mock

        return mock;
    }
}
