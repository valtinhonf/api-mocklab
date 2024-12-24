package com.mocklab.api.domains.mock.services;

import com.mocklab.api.adapter.input.dto.RequestNewMockDTO;
import com.mocklab.api.adapter.input.dto.ResponseMockDTO;
import com.mocklab.api.domains.mock.dtos.ProjectionMockProjectDTO;
import com.mocklab.api.domains.mock.entities.Mock;
import com.mocklab.api.domains.mock.repositories.MockRepository;
import com.mocklab.api.shared.exceptions.MockIsThisUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class MockServices {

    private static final Logger log = LoggerFactory.getLogger(MockServices.class);

    private MockRepository mockRepo;

    public MockServices(MockRepository pRepo){
        this.mockRepo = pRepo;
    }

    public ResponseMockDTO saveNew(RequestNewMockDTO newMock) throws Exception {
        try{
            log.info("Trying to save a new Mock {}", newMock.toString());

            Mock mock = mockRepo.save(Mock.convert(newMock));

            if (Objects.isNull(mock)){
                throw new Exception("Bugou!!");
            }
            return ResponseMockDTO.convert(mock);
        } catch (Exception e){
            throw e;
        }
    }

    public ResponseMockDTO update(Mock mock){
        try{
            log.info("Trying to update a Mock {}", mock.getIdmockpublic());

            Mock mockToUpdate = this.mockRepo.findByIdmockpublic(mock.getIdmockpublic()).orElseThrow(() -> new RuntimeException("Mock not found!"));
            mock.setIdmock(mockToUpdate.getIdmock());

            Mock mockUpdated = this.mockRepo.save(mock);

            return ResponseMockDTO.convert(mockUpdated);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ResponseMockDTO> findAllByUser(String idUser) {
        log.info("Trying retrieving user's {} mock", idUser);
        try{
            List<Mock> mocks = mockRepo.findAllByIduser(UUID.fromString(idUser));

            return ResponseMockDTO.convert(mocks);
        } catch (Exception e){
            throw e;
        }
    }

    public List<ProjectionMockProjectDTO> findAllByOrganizationAndUser(String idOrganization, String idUser) {
        log.info("Trying retrieving organizations's {} mock", idOrganization);
        try{
            List<ProjectionMockProjectDTO> mocks = mockRepo.listAllMocksFromOrganizationToHomeGrouped(UUID.fromString(idOrganization), UUID.fromString(idUser));

            return mocks;
        } catch (Exception e){
            throw e;
        }
    }

    public ResponseMockDTO findById(String idPublicMock){
        Mock mock = this.mockRepo.findByIdmockpublic(UUID.fromString(idPublicMock)).orElseThrow(() -> new RuntimeException("Mock not found!"));

        return ResponseMockDTO.convert(mock);
    }

    public void deleteMock(String idUser, String idPublicMock) {
        try{
            Mock mock = this.mockRepo.findByIdmockpublic(UUID.fromString(idPublicMock)).orElseThrow(() -> new RuntimeException("Mock not found!"));

            if (!mock.getIduser().equals(UUID.fromString(idUser))){
                throw new MockIsThisUser();
            }

            mockRepo.deleteById(mock.getIdmock());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
