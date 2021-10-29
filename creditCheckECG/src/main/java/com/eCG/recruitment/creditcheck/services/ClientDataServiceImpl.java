package com.eCG.recruitment.creditcheck.services;

import com.eCG.recruitment.creditcheck.models.ClientDetails;
import com.eCG.recruitment.creditcheck.repository.ClientDataRepository;
import com.eCG.recruitment.creditcheck.exception.ClientNotFoundException;
import com.eCG.recruitment.creditcheck.exception.NoDataFoundException;
import com.eCG.recruitment.creditcheck.exception.SaveClientDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDataServiceImpl implements ClientDataService {
    private final ClientDataRepository clientDataRepository;

    @Autowired
    ClientDataServiceImpl(ClientDataRepository clientDataRepository) {
        this.clientDataRepository = clientDataRepository;
    }

    @Override
    public ClientDetails getClientData(String dateOfBirth , String phoneNo) {

        ClientDetails clientDetails=clientDataRepository.getClientDataByPhoneAndDob(phoneNo,dateOfBirth);
        if(null!=clientDetails)
            return clientDetails;
       else throw  new ClientNotFoundException(phoneNo,dateOfBirth);
    }

    @Override
    public List<ClientDetails> getAllClients() {
        List<ClientDetails> clientDetailsList= clientDataRepository.findAll();

        if (clientDetailsList.isEmpty()) {

            throw new NoDataFoundException();
        }

        return clientDetailsList;
    }

    @Override
    public void saveClientData(List<ClientDetails> clientDataList) {

        try {
            for (int i = 1; i < clientDataList.size(); i++) {
                clientDataRepository.save(clientDataList.get(i));
            }
        }
        catch (Exception ex)
        {
            throw new SaveClientDataException();
        }
    }
}
