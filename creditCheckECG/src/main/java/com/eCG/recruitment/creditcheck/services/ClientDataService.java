package com.eCG.recruitment.creditcheck.services;

import com.eCG.recruitment.creditcheck.models.ClientDetails;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ClientDataService {
    List<ClientDetails> getAllClients();

    ClientDetails getClientData(String dateOfBirth, String phoneNo) throws ParseException;

    void saveClientData(List<ClientDetails> clientDataList);
}
