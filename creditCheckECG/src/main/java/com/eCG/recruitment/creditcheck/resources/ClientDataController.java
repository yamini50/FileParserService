package com.eCG.recruitment.creditcheck.resources;

import com.eCG.recruitment.creditcheck.models.ClientDetails;
import com.eCG.recruitment.creditcheck.services.ClientDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientData")
public class ClientDataController {
    @Autowired
    ClientDataService clientDataService;

    @GetMapping(value = "/getClientDetails")
    @ResponseStatus(HttpStatus.OK)
    public ClientDetails getClientDetailsById(@RequestParam(value = "dob", required = true) String dateOfBirth, @RequestParam(value = "phone-no", required = true) String phoneNo) {
        ClientDetails clientData = null;
        try {
            clientData = clientDataService.getClientData(dateOfBirth, phoneNo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return clientData;
    }

    @GetMapping(value = "/getAllClients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDetails> getAllClients() {
        return clientDataService.getAllClients();
    }
}
